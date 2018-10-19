package org.rpc.client.provider;

import static org.rpc.common.protocal.Protocol.RPC_REQUEST;
import io.netty.channel.ChannelHandlerContext;

import org.rpc.remoting.ConnectionUtils;
import org.rpc.remoting.model.NettyRequestProcessor;
import org.rpc.remoting.model.RemotingTransporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description provider端
 * @author Zhangdada
 * @version v1.0
 * @date 2018年10月18日
 */
public class DefaultProviderRPCProcessor implements NettyRequestProcessor {

	private static final Logger logger = LoggerFactory.getLogger(DefaultProviderRPCProcessor.class);

	private DefaultProvider defaultProvider;

	public DefaultProviderRPCProcessor(DefaultProvider defaultProvider) {
		this.defaultProvider = defaultProvider;
	}

	@Override
	public RemotingTransporter processRequest(ChannelHandlerContext ctx, RemotingTransporter request) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("receive request, {} {} {}", //
					request.getCode(), //
					ConnectionUtils.parseChannelRemoteAddr(ctx.channel()), //
					request);
		}

		switch (request.getCode()) {
		case RPC_REQUEST:
			// 这边稍微特殊处理一下，可以返回null,我们不需要叫外层代码帮我们writeAndFlush
			// 发出请求，因为我们持有channel，这样做rpc可以更加灵活一点
			this.defaultProvider.handlerRPCRequest(request, ctx.channel());
			break;
		}
		return null;
	}

}

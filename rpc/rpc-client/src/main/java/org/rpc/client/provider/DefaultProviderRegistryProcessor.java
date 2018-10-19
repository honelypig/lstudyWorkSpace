package org.rpc.client.provider;

import static org.rpc.common.protocal.Protocol.AUTO_DEGRADE_SERVICE;
import static org.rpc.common.protocal.Protocol.DEGRADE_SERVICE;
import io.netty.channel.ChannelHandlerContext;

import org.rpc.remoting.ConnectionUtils;
import org.rpc.remoting.model.NettyRequestProcessor;
import org.rpc.remoting.model.RemotingTransporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description provider端注册的处理器
 * @author Zhangdada
 * @version v1.0
 * @date 2018年10月18日
 */
public class DefaultProviderRegistryProcessor implements NettyRequestProcessor {

	private static final Logger logger = LoggerFactory.getLogger(DefaultProviderRegistryProcessor.class);

	private DefaultProvider defaultProvider;

	public DefaultProviderRegistryProcessor(DefaultProvider defaultProvider) {
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
		case DEGRADE_SERVICE:
			return this.defaultProvider.handlerDegradeServiceRequest(request, ctx.channel(), DEGRADE_SERVICE);
		case AUTO_DEGRADE_SERVICE:
			return this.defaultProvider.handlerDegradeServiceRequest(request, ctx.channel(), AUTO_DEGRADE_SERVICE);
		}
		return null;
	}

}

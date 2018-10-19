package org.rpc.client.provider.model;

import io.netty.channel.ChannelHandlerContext;

import org.rpc.client.provider.DefaultProvider;
import org.rpc.remoting.model.NettyChannelInactiveProcessor;

/**
 * 
 * @Description provider的netty inactive触发的事件
 * @author Zhangdada
 * @version v1.0
 */
public class DefaultProviderInactiveProcessor implements NettyChannelInactiveProcessor {

	private DefaultProvider defaultProvider;

	public DefaultProviderInactiveProcessor(DefaultProvider defaultProvider) {
		this.defaultProvider = defaultProvider;
	}

	@Override
	public void processChannelInactive(ChannelHandlerContext ctx) {
		defaultProvider.setProviderStateIsHealthy(false);
	}

}

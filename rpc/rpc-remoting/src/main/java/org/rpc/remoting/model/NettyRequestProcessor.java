package org.rpc.remoting.model;

import io.netty.channel.ChannelHandlerContext;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public interface NettyRequestProcessor {
	RemotingTransporter processRequest(ChannelHandlerContext ctx, RemotingTransporter request)
            throws Exception;
}

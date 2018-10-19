package org.rpc.remoting.model;

import io.netty.channel.ChannelHandlerContext;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public interface NettyRpcRequestProcessor {
	void processRPCRequest(ChannelHandlerContext ctx, RemotingTransporter request);
}

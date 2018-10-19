package org.rpc.remoting.model;

import org.rpc.common.exception.remoting.RemotingSendRequestException;
import org.rpc.common.exception.remoting.RemotingTimeoutException;

import io.netty.channel.ChannelHandlerContext;

/**
 * @Description 处理channel关闭或者inactive的状态的时候的改变
 * @author Zhangdada
 * @version v1.0
 */
public interface NettyChannelInactiveProcessor {
	void processChannelInactive(ChannelHandlerContext ctx) throws RemotingSendRequestException, 
	RemotingTimeoutException, InterruptedException;
}

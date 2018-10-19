package org.rpc.remoting.watcher;

import io.netty.channel.ChannelHandler;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public interface ChannelHandlerHolder {
	ChannelHandler[] handlers();
}

package org.rpc.remoting.netty;

import java.util.concurrent.ExecutorService;

import org.rpc.common.exception.remoting.RemotingSendRequestException;
import org.rpc.common.exception.remoting.RemotingTimeoutException;
import org.rpc.common.utils.Pair;
import org.rpc.remoting.model.NettyChannelInactiveProcessor;
import org.rpc.remoting.model.NettyRequestProcessor;
import org.rpc.remoting.model.RemotingTransporter;

import io.netty.channel.Channel;

/**
 * @Description netty服务端的一些抽象方法
 * 1)作为服务端自然要处理来自客户端请求的一些请求，每一个请求都会有一个与之对应的处理器
 * 2)这样做的好处就是简化了netty的handler的配置，将handler中的业务逻辑放置到每一个对应的处理器中来
 * @author Zhangdada
 * @version v1.0
 */
public interface RemotingServer  extends BaseRemotingService{
void registerProecessor(final byte requestCode, final NettyRequestProcessor processor,final ExecutorService executor);
	
	void registerChannelInactiveProcessor(final NettyChannelInactiveProcessor processor,final ExecutorService executor);
	
	void registerDefaultProcessor(final NettyRequestProcessor processor, final ExecutorService executor);
	
    Pair<NettyRequestProcessor, ExecutorService> getProcessorPair(final int requestCode);

    RemotingTransporter invokeSync(final Channel channel, final RemotingTransporter request, final long timeoutMillis) throws InterruptedException, RemotingSendRequestException,
            RemotingTimeoutException;
}

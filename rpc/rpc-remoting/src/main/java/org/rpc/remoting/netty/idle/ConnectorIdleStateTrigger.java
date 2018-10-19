package org.rpc.remoting.netty.idle;

import org.rpc.remoting.model.Heartbeats;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
@ChannelHandler.Sharable
public class ConnectorIdleStateTrigger extends ChannelInboundHandlerAdapter {
	 @Override
	    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
	        if (evt instanceof IdleStateEvent) {
	            IdleState state = ((IdleStateEvent) evt).state();
	            if (state == IdleState.WRITER_IDLE) {
	                ctx.writeAndFlush(Heartbeats.heartbeatContent());
	            }
	        } else {
	            super.userEventTriggered(ctx, evt);
	        }
	    }
}

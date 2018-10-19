package org.rpc.remoting.netty.idle;

import org.rpc.common.exception.remoting.RemotingNoSighException;

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
public class AcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter {
	  @Override
	    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
	    	System.out.println("accpet heartbeat");
	        if (evt instanceof IdleStateEvent) {
	            IdleState state = ((IdleStateEvent) evt).state();
	            if (state == IdleState.READER_IDLE) {
	                throw new RemotingNoSighException("no sign");
	            }
	        } else {
	            super.userEventTriggered(ctx, evt);
	        }
	    }
}

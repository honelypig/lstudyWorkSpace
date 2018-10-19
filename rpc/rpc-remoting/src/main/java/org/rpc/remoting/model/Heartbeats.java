package org.rpc.remoting.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import static org.rpc.common.protocal.Protocol.HEAD_LENGTH;
import static org.rpc.common.protocal.Protocol.HEARTBEAT;
import static org.rpc.common.protocal.Protocol.MAGIC;
/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
@SuppressWarnings("deprecation")
public class Heartbeats {
	  private static final ByteBuf HEARTBEAT_BUF;
	    
	    static {
	        ByteBuf buf = Unpooled.buffer(HEAD_LENGTH);
	        buf.writeShort(MAGIC);
	        buf.writeByte(HEARTBEAT);
	        buf.writeByte(0);
	        buf.writeLong(0);
	        buf.writeInt(0);
	        HEARTBEAT_BUF = Unpooled.unmodifiableBuffer(Unpooled.unreleasableBuffer(buf));
	    }

	    /**
	     * Returns the shared heartbeat content.
	     */
	    public static ByteBuf heartbeatContent() {
	        return HEARTBEAT_BUF.duplicate();
	    }
}

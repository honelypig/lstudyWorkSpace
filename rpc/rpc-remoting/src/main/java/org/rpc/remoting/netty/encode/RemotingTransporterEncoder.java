package org.rpc.remoting.netty.encode;

import org.rpc.remoting.model.RemotingTransporter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import static org.rpc.common.protocal.Protocol.MAGIC;
import static org.rpc.common.serialization.SerializerHolder.serializerImpl;
/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public class RemotingTransporterEncoder extends MessageToByteEncoder<RemotingTransporter>{

	@Override
	protected void encode(ChannelHandlerContext ctx, RemotingTransporter msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		doEncodeRemotingTransporter(msg, out);
		
	}
	private void  doEncodeRemotingTransporter(RemotingTransporter msg, ByteBuf out) {
		byte[] body=serializerImpl().writeObject(msg.getCustomHeader());
		out.writeByte(MAGIC)  					//协议头
		.writeByte(msg.getTransporterType())	// 传输类型 sign 是请求还是响应
		.writeByte(msg.getCode()) 				// 请求类型requestcode 表明主题信息的类型，也代表请求的类型
		.writeLong(msg.getOpaque()) 			//requestId
		.writeInt(body.length)
		.writeBytes(body);
		
	}

}

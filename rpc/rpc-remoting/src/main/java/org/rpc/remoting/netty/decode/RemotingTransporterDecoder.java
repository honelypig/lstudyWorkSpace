package org.rpc.remoting.netty.decode;

import static org.rpc.common.protocal.Protocol.MAGIC;

import java.util.List;

import org.rpc.common.exception.remoting.RemotingContextException;
import org.rpc.common.protocal.Protocol;
import org.rpc.remoting.model.RemotingTransporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xerial.snappy.Snappy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * @Description Netty 对{@link RemotingTransporter}的解码器
 * @author Zhangdada
 * @version v1.0
 */
public class RemotingTransporterDecoder extends ReplayingDecoder<RemotingTransporterDecoder.State> {
	private static final Logger logger=LoggerFactory.getLogger(ReplayingDecoder.class);
	private static final int MAX_BODY_SIZE=1024*1024*5;
	private final Protocol header=new Protocol();
	public  RemotingTransporterDecoder() {
		super(State.HEADER_MAGIC);
	}
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		switch (state()) {
		case HEADER_MAGIC:
			checkMagic(in.readShort()); // MAGIC
			checkpoint(State.HEADER_TYPE);
			break;
		case HEADER_TYPE:
			header.setType(in.readByte());
			checkpoint(State.HEADER_SIGN);
			break;
		case HEADER_SIGN:
			header.setSign(in.readByte()); // 消息标志位
			checkpoint(State.HEADER_ID);
			break;
		case HEADER_ID:
			header.setId(in.readLong()); // 消息id
			checkpoint(State.HEADER_BODY_LENGTH);
			break;
		case HEADER_BODY_LENGTH:
			header.setBodyLength(in.readInt()); // 消息体长度
			checkpoint(State.HEADER_COMPRESS);
			break;
		case HEADER_COMPRESS:
			header.setCompress(in.readByte()); // 消息是否压缩
			checkpoint(State.BODY);
			break;
		case BODY:
			int bodyLength = checkBodyLength(header.getBodyLength());
			byte[] bytes = new byte[bodyLength];
			in.readBytes(bytes);
			if(header.getCompress() == Protocol.COMPRESS){
				bytes = Snappy.uncompress(bytes);
			}
			out.add(RemotingTransporter.newInstance(header.getId(), header.getSign(),header.getType(), bytes));
			
			break;

		default:
			break;
		}
		checkpoint(State.HEADER_MAGIC);
	}
	@SuppressWarnings("unused")
	private int checkBodyLength(int bodyLength) throws RemotingContextException {
		if (bodyLength > MAX_BODY_SIZE) {
            throw new RemotingContextException("body of request is bigger than limit value "+ MAX_BODY_SIZE);
        }
        return bodyLength;
	}
	
	private void checkMagic(short magic) throws RemotingContextException {
		if (MAGIC != magic) {
			logger.error("Magic is not match");
            throw new RemotingContextException("magic value is not equal "+MAGIC);
        }
	}

	enum State {
		HEADER_MAGIC, HEADER_TYPE, HEADER_SIGN, HEADER_ID, HEADER_BODY_LENGTH,HEADER_COMPRESS, BODY
	}

	/**
	 * note:
	 *  这个ReplayingDecoder有几个比较关键的方法说明一下
	 * 1）构造函数，它的构造函数，传递了一个枚举类型进去，这个是设置state()这个方法的初始值，这样可以进case HEADER_MAGIC的代码分支，首先先校验协议头，是否使我们定义的MAGIC,如果不是的情况下，我们就不进行解码，因为可能此信息与我们规定的不符
	 * 2）checkpoint的方法作用有两个，一是改变state的值的状态，二是获取到最新的读指针的下标
	 * 3）最后一步重新调用checkpoint(State.HEADER_MAGIC)，是把state的值重新设置为初始值HEADER_MAGIC，方便下次信息的解析读取
	 * 4）在switch case中主要做的事情，就是将byte[]转换成我们需要的RemotingTransporter的对象，且把主体信息的byte[]放到RemotingTransporter的父类ByteHolder中，关于这块的byte[]的序列化则放在具体的业务场景之下
	 */
}

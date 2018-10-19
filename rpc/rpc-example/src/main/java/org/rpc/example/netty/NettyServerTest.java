package org.rpc.example.netty;

import java.util.concurrent.Executors;

import static org.rpc.common.serialization.SerializerHolder.serializerImpl;
import org.rpc.common.protocal.Protocol;
import org.rpc.remoting.model.NettyRequestProcessor;
import org.rpc.remoting.model.RemotingTransporter;
import org.rpc.remoting.netty.NettyRemotingServer;
import org.rpc.remoting.netty.NettyServerConfig;

import io.netty.channel.ChannelHandlerContext;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public class NettyServerTest {
public static final byte TEST = -1;
	
	public static void main(String[] args) {
		
		NettyServerConfig config = new NettyServerConfig();
		config.setListenPort(18001);
		NettyRemotingServer server = new NettyRemotingServer(config);
		server.registerProecessor(TEST, new NettyRequestProcessor() {
			
			@Override
			public RemotingTransporter processRequest(ChannelHandlerContext ctx, RemotingTransporter transporter) throws Exception {
				transporter.setCustomHeader(serializerImpl().readObject(transporter.bytes(), TestCommonCustomBody.class));
				System.out.println(transporter);
				transporter.setTransporterType(Protocol.RESPONSE_REMOTING);
				return transporter;
			}
		}, Executors.newCachedThreadPool());
		server.start();
	}
}

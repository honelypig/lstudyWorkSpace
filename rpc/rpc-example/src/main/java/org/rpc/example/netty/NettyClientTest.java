package org.rpc.example.netty;

import org.rpc.common.exception.remoting.RemotingException;
import org.rpc.example.netty.TestCommonCustomBody.ComplexTestObj;
import org.rpc.remoting.model.RemotingTransporter;
import org.rpc.remoting.netty.NettyClientConfig;
import org.rpc.remoting.netty.NettyRemotingClient;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public class NettyClientTest {
public static final byte TEST = -1;
	
	public static void main(String[] args) throws InterruptedException, RemotingException {
		NettyClientConfig nettyClientConfig = new NettyClientConfig();
		NettyRemotingClient client = new NettyRemotingClient(nettyClientConfig);
		client.start();
		
		ComplexTestObj complexTestObj = new ComplexTestObj("attr1", 2);
		TestCommonCustomBody commonCustomHeader = new TestCommonCustomBody(1, "test",complexTestObj);
		
		RemotingTransporter remotingTransporter = RemotingTransporter.createRequestTransporter(TEST, commonCustomHeader);
		RemotingTransporter request = client.invokeSync("127.0.0.1:18001", remotingTransporter, 3000);
		System.out.println(request);
	}
}

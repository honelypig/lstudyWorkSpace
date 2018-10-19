package org.rpc.example.generic.test_5;

import org.rpc.client.provider.DefaultProvider;
import org.rpc.common.exception.remoting.RemotingException;

/**
 * 
 * @Description 测试单位时间的限流
 * @author Zhangdada
 * @version v1.0
 * @date 2018年10月18日
 */
public class ProviderTest {

	public static void main(String[] args) throws InterruptedException, RemotingException {

		DefaultProvider defaultProvider = new DefaultProvider();

		defaultProvider.serviceListenPort(8899) // 暴露服务的地址
				.publishService(new HelloServiceFlowControllerImpl()) // 暴露的服务
				.start(); // 启动服务

	}

}

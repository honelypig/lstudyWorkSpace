package org.rpc.example.generic.test_3;

import org.rpc.client.provider.DefaultProvider;
import org.rpc.common.exception.remoting.RemotingException;
import org.rpc.example.demo.service.ByeServiceImpl;
import org.rpc.example.demo.service.HelloSerivceImpl;
import org.rpc.remoting.netty.NettyClientConfig;
import org.rpc.remoting.netty.NettyServerConfig;

public class ProviderTest {

	public static void main(String[] args) throws InterruptedException, RemotingException {

		DefaultProvider defaultProvider = new DefaultProvider(new NettyClientConfig(), new NettyServerConfig());

		defaultProvider.registryAddress("127.0.0.1:18010") // 注册中心的地址
				.monitorAddress("127.0.0.1:19010") // 监控中心的地址
				.serviceListenPort(8899) // 暴露服务的地址
				.publishService(new HelloSerivceImpl(), new ByeServiceImpl()) // 暴露的服务
				.start(); // 启动服务
		/**
		 * DefaultProvider是Provider接口的具体的实现类，启动一个Provider最最规范的方式就是这样了，
		 * 至少给其设定如上代码的几个参数：
		 * 
		 * 1）注册中心地址，（其实可以没有，有一种场景就是服务消费者知道服务提供的地址，直接调用，不需要去注册中心上订阅，
		 * 不过这也是在特殊的情况下使用）
		 * 
		 * 2）监控中心的地址（也是非必要的，不过最好设置上）
		 * 
		 * 3）服务暴露的端口号，这个是必要的，否则消费者如何去与你建立连接进行服务消费活动呢
		 * 
		 * 4）要提供的服务，一切都准备好了，全部是这个服务准备的，如果你不设置你要提供的服务，不是逗别人玩吗
		 * 
		 * 5）start方法启动整个Provider的实例
		 */
	}

}

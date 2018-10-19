package org.rpc.example.generic.test_5;

import org.rpc.client.consumer.ConsumerClient;
import org.rpc.client.consumer.proxy.ProxyFactory;
import org.rpc.common.utils.UnresolvedAddress;

/**
 * 
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public class ConsumerTest {

	public static void main(String[] args) throws Exception {

		ConsumerClient client = new ConsumerClient();

		client.start();

		UnresolvedAddress addresses = new UnresolvedAddress("127.0.0.1", 8899);

		HelloService helloService = ProxyFactory.factory(HelloService.class).consumer(client)
				.addProviderAddress(addresses).timeoutMillis(3000l).newProxyInstance();

		for (int index = 1; index < 45; index++) {

			String str = helloService.sayHello("Lyncc");
			System.out.println("当前调用的次数是：" + index);
			System.out.println(str);
		}

	}

}

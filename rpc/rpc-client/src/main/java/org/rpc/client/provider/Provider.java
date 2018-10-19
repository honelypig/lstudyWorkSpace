package org.rpc.client.provider;

import org.rpc.common.exception.remoting.RemotingException;
import org.rpc.remoting.model.RemotingTransporter;

import io.netty.channel.Channel;

/**
 * @Description provider端的接口 服务提供者端需要提供以下接口 1)需要暴露哪些服务【必要】
 *              {@link Provider #publishService(Object...)} 2)暴露的服务在哪个端口上提供【必要】
 *              {@link Provider #serviceListenAddress(String)} 3)设置注册中心的地址【必要】
 *              {@link Provider #registryAddress(String)} 4)暴露启动服务提供者的方法【必须调用】
 *              {@link Provider #start()} 5)设置provider端提供的监控地址【非必要】
 *              {@link Provider #monitorAddress(String)}
 * @author Zhangdada
 * @version v1.0
 */
public interface Provider {
	/**
	 * 启动provider的实例
	 * 
	 * @throws RemotingException
	 * @throws InterruptedException
	 */
	void start() throws InterruptedException, RemotingException;

	/**
	 * 发布服务
	 * 
	 * @throws InterruptedException
	 * @throws RemotingException
	 */
	void publishedAndStartProvider() throws InterruptedException, RemotingException;

	/**
	 * 暴露服务的地址
	 * 
	 * @param port
	 * @return
	 */
	Provider serviceListenPort(int exposePort);

	/**
	 * 设置注册中心的地址 host:port,host1:port1
	 * 
	 * @param registryAddress
	 * @return
	 */
	Provider registryAddress(String registryAddress);

	/**
	 * 监控中心的地址，不是强依赖，不设置也没有关系
	 * 
	 * @param monitorAddress
	 * @return
	 */
	Provider monitorAddress(String monitorAddress);

	/**
	 * 需要暴露的接口
	 * 
	 * @param obj
	 */
	Provider publishService(Object... obj);

	/**
	 * 处理消费者的rpc请求
	 * 
	 * @param request
	 * @param channel
	 * @return
	 */
	void handlerRPCRequest(RemotingTransporter request, Channel channel);

}

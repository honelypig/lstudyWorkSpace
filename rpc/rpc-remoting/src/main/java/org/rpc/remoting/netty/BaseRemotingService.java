package org.rpc.remoting.netty;

import org.rpc.remoting.RPCHook;

/**
 * @Description RPC的回调钩子，在发送请求和接收请求的时候触发，这样做事增加程序的健壮性和灵活性
 * @author Zhangdada
 * @version v1.0
 */
public interface BaseRemotingService {
	/**
	 * Netty的一些参数的初始化
	 */
	void init();
	
	/**
	 * 启动Netty方法
	 */
	void start();
	
	/**
	 * 关闭Netty C/S 实例
	 */
	void shutdown();
	
	/**
	 * 注入钩子，Netty在处理的过程中可以嵌入一些方法，增加代码的灵活性
	 * @param rpcHook
	 */
	void registerRPCHook(RPCHook rpcHook);
}

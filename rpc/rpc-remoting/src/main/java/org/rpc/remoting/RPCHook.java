package org.rpc.remoting;

import org.rpc.remoting.model.RemotingTransporter;

/**
 * @Description  RPC的回调钩子，在发送请求和接收请求的时候触发，这样做事增加程序的健壮性和灵活性
 * @author Zhangdada
 * @version v1.0
 */
public interface RPCHook {
	//加了final，该参数不可以再赋值（实参传进来给形参，就相当于初始化完成）。。。。可以防止在方法里面不小心重新赋值，造成一些不必要的麻烦！！！
	void doBeforeRequest(final String remoteAddr, final RemotingTransporter request);

    void doAfterResponse(final String remoteAddr, final RemotingTransporter request,final RemotingTransporter response);
}

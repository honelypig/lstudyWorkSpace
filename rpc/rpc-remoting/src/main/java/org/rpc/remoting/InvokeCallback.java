package org.rpc.remoting;

import org.rpc.remoting.model.RemotingResponse;

/**
 * @Description 远程调用之后的回调函数
 * @author Zhangdada
 * @version v1.0
 */
public interface InvokeCallback {
	  void operationComplete(final RemotingResponse remotingResponse);
}

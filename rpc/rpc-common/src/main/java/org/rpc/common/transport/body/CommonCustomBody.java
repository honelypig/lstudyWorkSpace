package org.rpc.common.transport.body;

import org.rpc.common.exception.remoting.RemotingCommmonCustomException;
//先定义接口（然后某些类实现改接口后，再一些方法的参数类型为该接口的，这些实现类都可以被传递）
/**
 * @Description 网络传输对象的主体对象 
 * @author Zhangdada
 * @version v1.0
 */
public interface CommonCustomBody {
	void checkFields() throws RemotingCommmonCustomException;
}

package org.rpc.common.exception.remoting;

/**
 * @Description
 * @author Zhangdada
 * @version v1.0
 */
public class RemotingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemotingException(String message) {
        super(message);
    }
	
	/**
	 *  抛出一个异常
	 * @param message 异常的信息
	 * @param cause 造成异常的原因
	 */
    public RemotingException(String message, Throwable cause) {
        super(message, cause);
    }
}

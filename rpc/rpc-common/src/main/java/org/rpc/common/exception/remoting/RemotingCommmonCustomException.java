package org.rpc.common.exception.remoting;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public class RemotingCommmonCustomException extends RemotingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemotingCommmonCustomException(String message) {
		super(message);
	}
	  public RemotingCommmonCustomException(String message, Throwable cause) {
	        super(message, cause);
	    }
}

package org.rpc.common.exception.remoting;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public class RemotingSendRequestException extends RemotingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3732533942298701876L;

	 public RemotingSendRequestException(String addr) {
	        this(addr, null);
    }


    public RemotingSendRequestException(String addr, Throwable cause) {
        super("send request to <" + addr + "> failed", cause);
    }
}

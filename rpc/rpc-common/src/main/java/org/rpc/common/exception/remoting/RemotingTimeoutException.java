package org.rpc.common.exception.remoting;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public class RemotingTimeoutException extends RemotingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3089082666971875707L;


	public RemotingTimeoutException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	 public RemotingTimeoutException(String addr, long timeoutMillis) {
	        this(addr, timeoutMillis, null);
    }


    public RemotingTimeoutException(String addr, long timeoutMillis, Throwable cause) {
        super("wait response on the channel <" + addr + "> timeout, " + timeoutMillis + "(ms)", cause);
    }
}

package org.rpc.common.exception.rpc;

/**
 * 
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 * @date 2018年10月18日
 */
public class NoServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7743840667451216980L;
	
	public NoServiceException() {
        super();
    }

    public NoServiceException(String message) {
        super(message);
    }

    public NoServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoServiceException(Throwable cause) {
        super(cause);
    }

}

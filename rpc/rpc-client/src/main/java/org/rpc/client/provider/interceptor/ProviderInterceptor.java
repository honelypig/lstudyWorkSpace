package org.rpc.client.provider.interceptor;

/**
 * 
 * @Description 拦截器
 * @author Zhangdada
 * @version v1.0
 */
public interface ProviderInterceptor {
	
    void beforeInvoke(String methodName, Object[] args);

    void afterInvoke(String methodName, Object[] args, Object result);

}

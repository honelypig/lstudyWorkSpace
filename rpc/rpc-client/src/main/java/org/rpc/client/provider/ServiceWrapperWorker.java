package org.rpc.client.provider;

import java.util.List;

import org.rpc.client.provider.interceptor.ProviderProxyHandler;
import org.rpc.client.provider.model.ServiceWrapper;

/**
 * @Description TODO
 * @author Zhangdada
 * @version v1.0
 */
public interface ServiceWrapperWorker {
	ServiceWrapperWorker provider(Object serviceProvider);
	
	ServiceWrapperWorker provider(ProviderProxyHandler proxyHandler,Object serviceProvider);
	
	List<ServiceWrapper> create();
}

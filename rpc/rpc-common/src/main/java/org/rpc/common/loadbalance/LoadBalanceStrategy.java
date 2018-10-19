package org.rpc.common.loadbalance;

/**
 * 
 * @Description 负载均衡的访问策略
 * @author Zhangdada
 * @version v1.0
 */

public enum LoadBalanceStrategy {
	
	RANDOM, //随机
	WEIGHTINGRANDOM, //加权随机
	ROUNDROBIN, //轮询

}

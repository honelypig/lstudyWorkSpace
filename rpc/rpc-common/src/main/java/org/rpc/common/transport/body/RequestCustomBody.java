package org.rpc.common.transport.body;

import java.util.concurrent.atomic.AtomicLong;

import org.rpc.common.exception.remoting.RemotingCommmonCustomException;

/**
 * @Description 远程调用的参数
 * @author Zhangdada
 * @version v1.0
 */
public class RequestCustomBody implements CommonCustomBody {

	private static final AtomicLong invokeIdGenerator = new AtomicLong(0);

	private final long invokeId; //调用的Id,全局只有一个
	private String serviceName;  //调用的服务名
	private Object[] args;       //调用服务的参数
	private long timestamp;      //调用的时间
/*
 * 1）invokeId，虽然在我这个RPC中并没有实现多大的价值，但这是我的能力的原因，但这个参数却是不可或缺的，
 * 因为有过远程调用使用经历的人，在线上出问题的时候，最最痛苦的就是定位问题了，而一般企业的业务都是链式调用，
 * A系统调用B,B调用C，C调用D,E,往往排查问题的时候，某某接口调用不同的时候，都不知道哪里出问题了，
 * 需要一步步地找相关的负责人一一确认，这个过程是很痛苦的，很有可能是跨部门的，不好协调，
 * 一个问题一个bug能需要半天的时间才能解决，这就是普通RPC所缺少的链路监控的功能，加入在这种链式调用的场景下，所有的调用日记，
 * 能够按照这个invokeId做归类的话，不管是用ElasticSearch还是Hbase，Mysql等等记录方法，加上索引，有个监控系统，
 * 这样就可以很简单的找出问题的所在，是哪个环节出了问题了，这样可以大大的加快排查问题的速度，
 * 很多S级互联网公司都实现了这个功能，本人暂时没有研究过，不过现在已经有很多开源了，大家可以自主调研，一起学习
 * 2）serviceName，这个很好理解，在上几个小节我们说过自定义Annotation绑定了某个具体的方法，所以一个serviceName是绑定一个方法的，获取到serviceName
 * ，我们可以确定唯一的方法，因为远程调用的本质还是调用某个方法
 * 3）args，这个不用多说，调用方法的入参
 * 4）timestamp调用的时间戳，这个时间应该在调用端的时候就形成了，一个远程调用的时间统计应该是从请求发出和接收到响应，这个时间应该算是一个完整的调用流程
 */
	public RequestCustomBody() {
		this(invokeIdGenerator.getAndIncrement());
	}
	
	public RequestCustomBody(long invokeId) {
		this.invokeId = invokeId;
	}
	
	

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getInvokeId() {
		return invokeId;
	}
	
	@Override
	public void checkFields() throws RemotingCommmonCustomException {
	}

}

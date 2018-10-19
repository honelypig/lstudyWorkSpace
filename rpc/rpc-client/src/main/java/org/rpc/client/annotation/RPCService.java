package org.rpc.client.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Description 服务提供端提供服务的Annotation
 * @author Zhangdada
 * @version v1.0
 */
@Retention(RetentionPolicy.RUNTIME)// 注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解。
@Target({ ElementType.METHOD })// @Target 用来说明该注解可以被声明在那些元素之前。METHOD说明该注解只能被声明在一个类的方法前。
@Documented
public @interface RPCService {

	
	public String serviceName() default "";					//服务名
	public int weight() default 50;							//负载访问权重服务的权重，这个是很必要的，应该每个系统的线上实例肯定不止一台，而每一台实例的性能也不一样，有些服务器的性能好一点，内存大一点，可以设置大一点，最大100，最小1，这样在服务端调用该实例的时候，默认是使用加权随机负载均衡的算法，去随机访问服务提供端的
	public String responsibilityName() default "system";	//负责人名
	public int connCount() default 1;						//单实例连接数，注册中心该参数有效，直连无效.该连接数表示的是一个Consumer实例与一个Provider实例之间连接数，一般情况下，一个连接就够用了，特殊情况下，可以设置多个链接
	public boolean isVIPService() default false;			//是否是VIP服务
	public boolean isSupportDegradeService() default false; //是否支持降级
	public String degradeServicePath() default "";			//如果支持降级，降级服务的路径
	public String degradeServiceDesc() default "";			//降级服务的描述
	public boolean isFlowController() default true;		    //是否单位时间限流
	public long maxCallCountInMinute() default 100000;		//单位时间的最大调用量
	
}

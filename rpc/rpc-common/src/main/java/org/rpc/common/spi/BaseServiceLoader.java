package org.rpc.common.spi;

import java.util.ServiceLoader;

/**
 * @Description 主要是用来加载spi的
 * @author Zhangdada
 * @version v1.0
 * @link http://www.cnblogs.com/zhongkaiuu/articles/5040971.html
 */
public final class BaseServiceLoader {
  public static <S> S load(Class<S> serviceClass) {
        return ServiceLoader.load(serviceClass).iterator().next();
    }
  //使用的是java spi，是JDK内置的一种服务提供发现机制。
  //运行流程是运用java.util.ServiceLoader这个类的load方法去在src/META-INF/services/寻找对应的全路径接口名称的文件，然后在文件中找到对应的实现方法并注入实现
  // 例如接口A  ServiceLoader.load(a)就能找出全部接口A的实现类 ，返回体是 ServiceLoader<A> 
}

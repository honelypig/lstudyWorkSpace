package org.rpc.common.serialization;

import org.rpc.common.spi.BaseServiceLoader;

/**
 * @Description 使用spi加载序列化接口的实现类，本项目有三个实现类，使用的类要在service里面配置
 * @author Zhangdada
 * @version v1.0
 */
public class SerializerHolder {
    // SPI
    private static final Serializer serializer = BaseServiceLoader.load(Serializer.class);

    public static Serializer serializerImpl() {
        return serializer;
    }
}

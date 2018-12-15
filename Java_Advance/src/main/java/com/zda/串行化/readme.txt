网络间传输数据时，需要将数据转化为二进制才能传输。
串行化也叫序列化:就是把存在于内存的对象数据转化成可以保存成硬盘文件形式去储存

反串行化也叫反序列化:就是把串行化后硬盘文件加载到内存，重新变成对象数据,他们都是以字节流的方式在网络上传输的
1.avro串行化
2.protobuf串行化
3.java串行化
4.fastjson
5.kryo

spi机制.
    1.编写统一接口org.rpc.common.serialization.Serializer
    2.编写接口的多个实现类
        org.rpc.common.serialization.proto.ProtoStuffSerializer
        org.rpc.common.serialization.fastjson.FastjsonSerializer
        org.rpc.common.serialization.kryo.KryoSerializer
    3.在src/main/resources/META-INF/services/路径下添加一个文件
        名称为接口的全路径+接口名。如 org.rpc.common.serialization.Serializer
         文件的内容为接口的实现类。如 org.rpc.common.serialization.proto.ProtoStuffSerializer
    意思就是，当我们去调用这个接口的时候，就会去使用接口的这个实现类。
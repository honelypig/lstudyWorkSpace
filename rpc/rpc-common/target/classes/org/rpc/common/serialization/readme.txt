	java原生态的java.io.Serializable就对序列化提供了支持，不过性能和序列化的结果并不是很让人满意的，在众多的序列化工具中，
例如protostuff。kryo,fastjson等等，N多序列化的工具，估计java原生的是比较差的（如果不差，这些第三方的序列化的工具也不会出现了）
有人说，不支持多种序列化方式的RPC框架，不是好的RPC框架，
所以我们还是实现几个吧，我们基于SPI的方式(SPI 简介http://www.cnblogs.com/softlin/p/4321955.html)去实现protostuff，
fastjson，kryo这三种序列化方式

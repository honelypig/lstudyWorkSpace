package org.rpc.example.serializer;

import org.rpc.example.netty.TestCommonCustomBody;
import org.rpc.example.netty.TestCommonCustomBody.ComplexTestObj;
import static org.rpc.common.serialization.SerializerHolder.serializerImpl;

/**
 * @Description 
 * 1)使用protoStuff序列化测试
 * 修改org.rpc.common.serialization.Serializer中的内容为：
 * org.rpc.common.serialization.proto.ProtoStuffSerializer
 * 
 * 2)使用fastjson序列化测试
 * 修改org.rpc.common.serialization.Serializer中的内容为：
 * org.rpc.common.serialization.fastjson.FastjsonSerializer
 * 
 * 3)使用kryo序列化测试
 * 修改org.rpc.common.serialization.Serializer中的内容为：
 * org.rpc.common.serialization.kryo.KryoSerializer
 * @author Zhangdada
 * @version v1.0
 * 
 */
public class SerializerTest {
	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		
		for(int i = 0;i < 100000;i++){
			ComplexTestObj complexTestObj = new ComplexTestObj("attr1", 2);
			TestCommonCustomBody commonCustomHeader = new TestCommonCustomBody(1, "test",complexTestObj);
			System.out.println(complexTestObj);
			System.out.println(commonCustomHeader);
			byte[] bytes = serializerImpl().writeObject(commonCustomHeader);
			
			TestCommonCustomBody body = serializerImpl().readObject(bytes, TestCommonCustomBody.class);
			System.out.println(body.getComplexTestObj());
			System.out.println(body);
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println((endTime - beginTime));

	}
}

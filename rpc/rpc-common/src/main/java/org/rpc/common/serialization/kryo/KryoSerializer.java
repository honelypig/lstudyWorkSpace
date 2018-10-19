package org.rpc.common.serialization.kryo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.rpc.common.serialization.Serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

/**
 * @Description 使用Kryo序列化
 * 需要实现java.io.Serializable接口
 * @author Zhangdada
 * @version v1.0
 */
public class KryoSerializer implements Serializer {

	@Override
	public <T> byte[] writeObject(T obj) {
		Kryo kryo=new Kryo();
		kryo.setReferences(false);
		kryo.register(obj.getClass(),new JavaSerializer());
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		Output output=new Output(baos);
		kryo.writeClassAndObject(output, obj);
		output.flush();
		output.close();
		
		byte[] b= baos.toByteArray();
		try {
			baos.flush();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public <T> T readObject(byte[] bytes, Class<T> clazz) {
		System.out.println("KryoSerializer deserializer");
		Kryo kryo = new Kryo();
		kryo.setReferences(false);
		kryo.register(clazz, new JavaSerializer());
		
		ByteArrayInputStream bais=new ByteArrayInputStream(bytes);
		
		Input input =new Input(bais);
		
		return (T) kryo.readClassAndObject(input);
	}

}

package com.zda.IO;

import com.zda.串行化.javaSerializ.Person;

import java.io.*;
import java.util.ArrayList;

public class IOTest {
    public static void main(String args[]) throws Exception {
        // writeFile();
        //readFile();
        writeAndRead();
    }

    //文件的读入与写出
    public static void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream("E:/fileout");
        String s = "hello world";
        fos.write(s.getBytes());
        fos.flush();
        fos.close();
    }

    public static void readFile() throws IOException {
        // 根据path路径实例化一个输入流的对象
        FileInputStream fis = new FileInputStream("E:/fileout");
        String result = "";
        //2. 返回这个输入流中可以被读的剩下的bytes字节的估计值；
        int size = fis.available();
        //3. 根据输入流中的字节数创建byte数组；
        byte[] array = new byte[size];
        //4.把数据读取到数组中；
        fis.read(array);

        //5.根据获取到的Byte数组新建一个字符串，然后输出；
        result = new String(array);
        System.out.println(result);
    }

    //对象的流读写
    public static void writeAndRead() throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setAge("12");
        person.setId(1);
        person.setName("张三");
        person.setSex("男");

        ByteArrayOutputStream baos=new ByteArrayOutputStream();

        ObjectOutputStream oos=new ObjectOutputStream(baos);

        oos.writeObject(person);//需要这个对象是serializable的

        ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());

        ObjectInputStream ois=new ObjectInputStream(bais);
        Person person2=(Person) ois.readObject();
        System.out.println(person2.getName()+"---"+person2.getAge());

    }

}

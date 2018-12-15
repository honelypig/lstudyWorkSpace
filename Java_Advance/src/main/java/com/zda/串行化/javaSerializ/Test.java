package com.zda.串行化.javaSerializ;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        testJavaSerial();
    }

    /**
     * java序列化比较简单，通常不需要编写保存和恢复对象状态的定制代码。
     * 实现java.io.Serializable接口的类对象可以转换成字节流或从 字节流恢复，不需要在类中增加任何代码。
     * 只有极少数情况下才需要定制代码保存或恢复对象状态。
     * 这里要注意：不是每个类都可序列化，有些类是不能序列化的， 例如涉及线程的类与特定JVM有非常复杂的关系。
     */
    public static void testJavaSerial() throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setAge("12");
        person.setId(1);
        person.setName("张三");
        person.setSex("男");
        //串行化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // FileOutputStream f = new FileOutputStream("E:\\serial");//将数据写到文件中
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(person);

        //反串行化
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        //从文件中反序列化 string 对象和 date 对象
        //  FileInputStream in = new FileInputStream("E:\\serial");
        ObjectInputStream ois = new ObjectInputStream(bais);//用文件的话，将bais改为in
        Person p2 = (Person) ois.readObject();
        System.out.println(p2.getName()+","+p2.getAge()+","+p2.getSex()+","+p2.getId());
    }

}

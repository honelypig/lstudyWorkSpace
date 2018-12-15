package com.zda;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONWriter;

import java.io.*;
import java.util.ArrayList;

public class FastJsonTest {
    public static void main(String args[]) throws IOException {
        write();
        //read();
    }
    public static void write() throws IOException {
        Person person= new Person();
        person.setId(1);
        person.setNamne("张三");
        Person son1= new Person();
        son1.setId(2);
        son1.setNamne("son1");
        Person son2= new Person();
        son2.setId(3);
        son2.setNamne("son2");
        person.setSon(new ArrayList<>());
        person.getSon().add(son1);
        person.getSon().add(son2);
        JSONWriter jsonWriter=new JSONWriter(new FileWriter("E:/fastjson"));
        jsonWriter.startObject();
        jsonWriter.writeObject(person);
        jsonWriter.endObject();
        jsonWriter.close();
//        String json=JSON.toJSONString(person, SerializerFeature.WriteClassName);
//        System.out.println(json);
//      byte[] per=  json.getBytes();
//
//        FileOutputStream fos=new FileOutputStream("E:/fastjson");
//        fos.write(per);
//        fos.flush();
//        fos.close();
    }
    public static void read() throws IOException {
        FileInputStream fis=new FileInputStream("E:/fastjson");
        InputStreamReader isr=new InputStreamReader(fis);
        BufferedReader sr=new BufferedReader(isr);
        StringBuilder sb=new StringBuilder();
        String tmp=null;
        while ((tmp=sr.readLine())!=null){
            sb.append(tmp);
        }
        sr.close();
        Person p=JSON.parseObject(sb.toString(),Person.class);
        System.out.println(p.getNamne());
        p.getSon().forEach(a->{
            System.out.println(a.getNamne()+","+a.getId());
        });

    }
}

package com.zda.串行化.fastjson;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.zda.utils.dto.Person;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.util.ArrayList;

/**
 * 实体类的路径不能包含中文,而且实体类必须有构造器
 */
public class FastJsonTest {
    public static void main(String args[]) throws IOException {
        //write();
        read();
    }

    public static void write() throws IOException {
        Person person = new Person();
        person.setId(1);
        person.setNamne("张三");
        Person son1 = new Person();
        son1.setId(2);
        son1.setNamne("son1");
        Person son2 = new Person();
        son2.setId(3);
        son2.setNamne("son2");
        person.setSon(new ArrayList<>());
        person.getSon().add(son1);
        person.getSon().add(son2);
        JSONWriter jsonWriter = new JSONWriter(new FileWriter("E:/fastjson"));
        jsonWriter.config(SerializerFeature.WriteClassName,false);
        jsonWriter.startObject();
        jsonWriter.writeObject(person);
        jsonWriter.endObject();
        jsonWriter.close();
    }

    public static void read() throws IOException {
        JSONReader reader = new JSONReader(new FileReader("E:/fastjson"));
        reader.startObject();
        Person p = reader.readObject(Person.class);
        reader.endObject();
        reader.close();
        System.out.println(p.getNamne());
        p.getSon().forEach(a -> {
            System.out.println(a.getNamne() + "," + a.getId());
        });
    }
}

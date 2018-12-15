package com.zda.串行化.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class KryoTest {
    public static void main(String args[]) throws FileNotFoundException {
        // write();
        read();
    }

    public static void write() throws FileNotFoundException {
        Kryo kryo = new Kryo();
        Person person = new Person();
        person.setId(1);
        person.setNamne("parent");
        Person son1 = new Person();
        son1.setId(2);
        son1.setNamne("son1");
        Person son2 = new Person();
        son2.setId(3);
        son2.setNamne("son2");
        List<Person> son = new ArrayList<>();
        son.add(son1);
        son.add(son2);
        person.setSon(son);
        Output output = new Output(new FileOutputStream("E:/kryo"));
        kryo.writeObject(output, person);
        output.flush();
    }

    public static void read() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("E:/kryo");
        Input input = new Input(fis);
        Kryo kryo = new Kryo();
        Person p = kryo.readObject(input, Person.class);
        System.out.println(p.getId() + "," + p.getNamne());
        p.getSon().forEach(son -> {
            System.out.println(" |--" + son.getNamne() + "," + son.getId());
        });
    }
}

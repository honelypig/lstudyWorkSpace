package com.zda.串行化.protobuf;

import com.example.protobuf.depts.DeptsProtos;

import static com.example.protobuf.depts.DeptsProtos.Deparment;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.protobuf.tutorial.AddressBookProtos.Person;

public class ProtobufTest {
    public static void main(String args[]) throws Exception {
        readDept();
    }
    public static void writePerson() throws IOException {
        Person person = Person.newBuilder()
                .setId(1)
                .setEmail("www.9050@qq.com")
                .setName("张三")
                .addPhone(Person.PhoneNumber.newBuilder()
                        .setNumber("99999")
                        .setType(Person.PhoneType.HOME)
                        .build()
                ).build();
        //writting data to a file
        FileOutputStream fos=new FileOutputStream("E:/person");
        person.writeTo(fos);
        fos.close();
    }

    public static void readPerson() throws IOException {
        Person person=Person.parseFrom(new FileInputStream("E:/person"));
        System.out.println(person.getName());
        System.out.println(person.toString());
    }
    
    public static void writeDeptAndRead ()throws Exception{
        Deparment.Person p= Deparment.Person.newBuilder()
                .setId(1)
                .setName("张三").setType(Deparment.PersonType.LEADER)
                .build();
        Deparment.Person p2= Deparment.Person.newBuilder()
                .setId(2)
                .setName("李四").setType(Deparment.PersonType.ORDINARY)
                .build();
        Deparment deparment= Deparment.newBuilder()
                .setId(1)
                .setName("研发部")
                .addPerson(p)
                .addPerson(p2)
                .build();
        DeptsProtos.Organise organise= DeptsProtos.Organise.newBuilder()
                .addDepart(deparment)
                .addDepart(deparment)
                .build();

        FileOutputStream fos=new FileOutputStream("E:/organise");
        organise.writeTo(fos);
        fos.close();
    }

    public static void readDept()throws Exception{
        DeptsProtos.Organise organise= DeptsProtos.Organise.parseFrom(new FileInputStream("E:/organise"));
        organise.getDepartList().forEach(a->{
            System.out.println("当前部门："+a.getName()+",id:"+a.getId());
            System.out.println("  |--人员：");
            a.getPersonList().forEach(b->{
                System.out.println("  |--   |-- "+b.getName()+",类型"+b.getType());
            });
        });
    }
}

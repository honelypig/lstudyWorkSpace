package com.zda.串行化.javaSerializ;

import java.io.Serializable;

/**
 *   实现自定义java串行化首先我们创建的类需要实现Serializable接口，
 * 这个接口只做标识作用，用来标识当前对象可被串行化，
 * 而且我们需要使用java io包下的两个类，
 * ObjectOutputStream/ObjectInputStream中的writeObject()/readObject()方法来实现串行化和反串行化
 */
public class Person implements Serializable {
    private int id;
    private String name;
    private String age;
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}

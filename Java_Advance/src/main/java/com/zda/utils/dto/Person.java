package com.zda.utils.dto;

import java.util.List;

public class Person {
    String namne;
    List<Person> son;
    int id;
    public Person() {

    }
    public String getNamne() {
        return namne;
    }

    public void setNamne(String namne) {
        this.namne = namne;
    }

    public List<Person> getSon() {
        return son;
    }

    public void setSon(List<Person> son) {
        this.son = son;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

package com.example.cfenv.reactive;

public class Person {

    private int id;
    private String name;
    // Constructor with getters and setters

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
        super();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }


}
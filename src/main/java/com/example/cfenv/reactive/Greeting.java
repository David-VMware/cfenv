package com.example.cfenv.reactive;

public class Greeting {

    public Greeting(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +
            " msg='" + getMsg() + "'" +
            "}";
    }

    private String msg;
}
package com.wordbe.java8to11.interfaces;

public class BookInterfaceImpl implements BookInterface {

    String name;

    public BookInterfaceImpl(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

//    @Override
//    public void printNameUpperCase() {
//        System.out.println(this.name.toUpperCase());
//    }
}

package com.wordbe.java8to11.functionalInterface.interfaces;

public class Impl implements Foo, Bar{

    @Override
    public void print() {
        System.out.println("재정의");
    }
}

package com.wordbe.java8to11.functionalInterface.interfaces;

public interface Foo {
    default void print() {
        System.out.println("foo");
    };
}

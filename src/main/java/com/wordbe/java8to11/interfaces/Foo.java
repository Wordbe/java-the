package com.wordbe.java8to11.interfaces;

public interface Foo {
    default void print() {
        System.out.println("foo");
    };
}

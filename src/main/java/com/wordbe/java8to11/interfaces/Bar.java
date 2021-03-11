package com.wordbe.java8to11.interfaces;

public interface Bar {
    default void print() {
        System.out.println("bar");
    };
}

package com.wordbe.java8to11.functionalInterface.interfaces;

public interface Bar {
    default void print() {
        System.out.println("bar");
    };
}

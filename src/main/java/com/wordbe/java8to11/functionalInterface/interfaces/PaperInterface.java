package com.wordbe.java8to11.functionalInterface.interfaces;

public interface PaperInterface {
    /**
     * @implSpec
     * Blue를 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println("Blue");
    };
}

package com.wordbe.java8to11.interfaces;

public interface BookInterface {

    void printName();

    String getName();

    /**
     * @implSpec
     * 아래 메소드는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    };

    static void printHelp() {
        System.out.println("help");
    }
}

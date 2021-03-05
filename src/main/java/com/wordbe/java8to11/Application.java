package com.wordbe.java8to11;

import com.wordbe.java8to11.functionalInterface.greet.Greeting;
import com.wordbe.java8to11.functionalInterface.interfaces.BookInterface;
import com.wordbe.java8to11.functionalInterface.interfaces.BookInterfaceImpl;

import java.util.Arrays;
import java.util.function.*;

public class Application {

    public static void main(String[] args) {

    }

    private static void interfaceExample() {
        BookInterface.printHelp();

        BookInterfaceImpl jack = new BookInterfaceImpl("jack");
        jack.printName();
        jack.printNameUpperCase();
    }

    private static void methodReference() {
        // 메소드 레퍼런스

        // static method 레퍼런스
        UnaryOperator<String> hi = Greeting::hi;
        System.out.println(hi.apply("Jack"));

        // 일반 메소드 레퍼런스
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("Jack"));

        // 생성자 레퍼런스
        Supplier<Greeting> defaultConstructor = Greeting::new;
        System.out.println(defaultConstructor.get().hello("Jack"));

        // 파라미터가 있는 생성자
        Function<String, Greeting> stringGreeting = Greeting::new;
        System.out.println(stringGreeting.apply("Team").getName());

        // 불특정 다수
        String[] names = {"Steve", "Team", "Musk"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));

    }

    private static void lambda() {
        int baseNumber = 10;

        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // 11
            }
        }

        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber); // 파라미터로 받은 baseNumber 출력
            }
        };

        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber); // baseNumber = 10
        };
        printInt.accept(10);

    }



    private static void functionalInterface() {
        Function<Integer, Integer> plus10 = integer -> integer + 10;
        Function<Integer, Integer> multiply2 = integer -> integer * 2;

        // multiply2 -> plus10
        Function<Integer, Integer> compositeFunc = plus10.compose(multiply2);

        // plus10 -> multiply2
        Function<Integer, Integer> compositeFunc2 = plus10.andThen(multiply2);

        System.out.println(compositeFunc2.apply(2));

        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);

        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        Predicate<String> startsWithJack = (s) -> s.startsWith("Jack");
        System.out.println(startsWithJack.test("Jack"));

        UnaryOperator<Integer> plus5 = (i) -> i + 5;
        Integer apply = plus5.apply(1);
        System.out.println(apply);
    }
}

package com.wordbe.java8to11.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Quiz5 {
    private static final String[] strArr = {"aaa","bb","c","dddd"};

    public void quiz1() {
        // 모든 문자열의 길이를 더한 결과를 출력하여라.
        int result = Arrays.stream(strArr)
                .mapToInt(String::length)
                .sum();
        System.out.println("result = " + result);
    }

    public void quiz2() {
        // 문자열 중에서 가장 긴 것의 길이를 출력하시오.
        int result = Arrays.stream(strArr)
                .mapToInt(String::length)
                .max()
                .getAsInt();
        System.out.println("result = " + result);
    }

    public void quiz3() {
        // 임의의 로또번호(1~45) 6개를 정렬해서 출력하시오.
        List<Integer> result = new Random().ints(1, 46)
                .distinct()
                .limit(6)
                .boxed()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public void quiz4() {
        // 두 개의 주사위를 굴려서 나온 눈의 합이 6인 경우를 모두 출력하시오.
        int[] dice1 = IntStream.rangeClosed(1, 6).toArray();
        int[] dice2 = IntStream.rangeClosed(1, 6).toArray();
        List<Integer[]> result = Arrays.stream(dice1)
                .boxed()
                .flatMap(i -> Arrays.stream(dice2)
                        .boxed()
                        .map(j -> new Integer[]{i, j}))
                .filter(arr -> arr[0] + arr[1] == 6)
                .collect(Collectors.toList());
        result.forEach(integers -> System.out.println(integers[0] + ", " + integers[1]));
    }
}

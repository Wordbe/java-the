package com.wordbe.java8to11.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Quiz3 {
    private static final List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    private static final List<Integer> numbers2 = Arrays.asList(3, 4);

    public void quiz1() {
        // 두 배열의 한 원소씩 담는 모든 숫자 쌍의 배열을 리스트로 반환하라.
        // [(1,3), (1,4), (2,3), (2,4), (3,3), (3,4)] 로 출력하여라.
        List<Integer[]> integers = numbers1.stream()
                .flatMap(m -> numbers2.stream()
                        .map(n -> new Integer[]{m, n}))
                .collect(Collectors.toList());
        String result = integers.stream()
                .map(a -> "(" + a[0] + "," + a[1] + ")")
                .collect(Collectors.joining(", "));
        System.out.println("result = " + result);
    }

    public void quiz2() {
        // 모든 숫자 쌍의 곱 중 가장 큰 값
        int result = numbers1.stream()
                .flatMap(m -> numbers2.stream()
                        .map(n -> m * n))
                .mapToInt(i -> i)
                .max()
                .getAsInt();
        System.out.println("result = " + result);
    }
}

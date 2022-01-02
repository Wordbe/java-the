package com.wordbe.java8to11.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz2 {
    private static final List<String> WORDS = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");

    public void quiz1() {
        // 접두사가 각각 몇개씩 있는가?
        Map<String, Integer> wordCount = new HashMap<>();
        WORDS.stream()
                .map(word -> word.substring(0, 1))
                .forEach(prefix -> wordCount.merge(prefix, 1, (oldValue, newValue) -> newValue += oldValue));

        wordCount.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public void quiz2() {
        // 단어의 길이가 2이상인 경우에만 모든 단어를 대문자로 변환하여 첫문자열만 가져와서 스페이스로 구분한 하나의 문자열로 합한 결과를 반환하여라.
        String result = WORDS.stream()
                .filter(word -> word.length() >= 2)
                .map(String::toUpperCase)
                .map(word -> word.substring(0, 1))
                .collect(Collectors.joining(" "));
        System.out.println("result = " + result);
    }
}

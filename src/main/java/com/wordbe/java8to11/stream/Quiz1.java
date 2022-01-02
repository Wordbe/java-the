package com.wordbe.java8to11.stream;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Quiz1 {
    private final List<String[]> csvLines = readCsvLines();

    private List<String[]> readCsvLines() {
        try {
            CSVReader csvReader = new CSVReader(
                    new FileReader(Objects.requireNonNull(getClass().getResource("/user.csv")).getFile()));
            csvReader.readNext();
            return csvReader.readAll();
        } catch (IOException | CsvException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void quiz1() {
        // 각 취미를 선호하는 인원은 몇 명인가?
        Map<String, Integer> result = new HashMap<>();
        csvLines.stream()
                .map(line -> line[1].trim())
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .forEach(hobby -> merge(result, hobby));

        // 출력
        result.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    private void merge(Map<String, Integer> result, String hobby) {
        if (result.containsKey(hobby)) {
            result.put(hobby, result.get(hobby) + 1);
        } else {
            result.put(hobby, 1);
        }
    }

    public void quiz2() {
        // 각 취미를 선호하는 정씨 성을 갖는 인원은 몇 명인가?
        Map<String, Integer> result = new HashMap<>();
        csvLines.stream()
                .filter(line -> line[0].startsWith("정"))
                .map(line -> line[1].trim())
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                .forEach(hobby -> merge(result, hobby));

        // 출력
        result.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public void quiz3() {
        // 소개 내용에 '좋아' 가 몇번 등장하는가?
        Integer count = csvLines.stream()
                .map(line -> countLike(line[2]))
                .reduce(0, Integer::sum);
        System.out.println("count = " + count);
    }

    private int countLike(String s) {
        return StringUtils.countMatches(s, "좋아");
    }
}

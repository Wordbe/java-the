package com.wordbe.java8to11.stream;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz6 {
    private static final Student[] stuArr = new Student[]{
            new Student("나자바", true, 1, 1, 300),
            new Student("김지미", false, 1, 1, 250),
            new Student("김자바", true, 1, 1, 200),
            new Student("이지미", false, 1, 2, 150),
            new Student("남자바", true, 1, 2, 100),
            new Student("안지미", false, 1, 2, 50),
            new Student("황지미", false, 1, 3, 100),
            new Student("강지미", false, 1, 3, 150),
            new Student("이자바", true, 1, 3, 200),
            new Student("나자바", true, 2, 1, 300),
            new Student("김지미", false, 2, 1, 250),
            new Student("김자바", true, 2, 1, 200),
            new Student("이지미", false, 2, 2, 150),
            new Student("남자바", true, 2, 2, 100),
            new Student("안지미", false, 2, 2, 50),
            new Student("황지미", false, 2, 3, 100),
            new Student("강지미", false, 2, 3, 150),
            new Student("이자바", true, 2, 3, 200) };

    @Data
    static class Student {
        private final String name;
        private final boolean isMale; // 성별
        private final int hak; // 학년
        private final int ban; // 반
        private final int score;

        public String toString() {
            return String.format("[%s, %s, %d학년 %d반, %3d점 ]", name, isMale ? "남" : "여", hak, ban, score);
        }

        // groupingBy()에서 사용 성적을 상,중,하 세 단계로 분류
        enum Level {
            HIGH, MID, LOW
        }
    }

    public void quiz1() {
        // stuArr 에서 불합격(150점 미만)한 학생을 남자와 여자로 구별하여라. (Map<Boolean, List<Student>>)
        Map<Boolean, List<Student>> result = Arrays.stream(stuArr)
                .filter(student -> student.getScore() < 150)
                .collect(Collectors.groupingBy(Student::isMale));
        result.entrySet().forEach(System.out::println);
    }

    public void quiz2() {
        // 각 반별 총점을 학년 별로 나누어 구하여라 (Map<Integer, Map<Integer, Integer>>)
        Map<Integer, Map<Integer, Integer>> result = Arrays.stream(stuArr)
                .collect(
                        Collectors.groupingBy(Student::getHak,
                                Collectors.groupingBy(Student::getBan, Collectors.summingInt(Student::getScore))));
        result.entrySet().forEach(System.out::println);
    }
}

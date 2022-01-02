package com.wordbe.java8to11.stream;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Quiz4 {
    private List<Transaction> transactions;

    public Quiz4() {
        init();
    }

    @Data
    static class Trader {
        private final String name;
        private final String city;
    }

    @Data
    static class Transaction {
        private final Trader trader;
        private final int year;
        private final int value;
    }

    private void init() {
        Trader kyu = new Trader("Kyu", "Seoul");
        Trader ming = new Trader("Ming", "Gyeonggi");
        Trader hyuk = new Trader("Hyuk", "Seoul");
        Trader hwan = new Trader("Hwan", "Busan");
        transactions = Arrays.asList(
                new Transaction(kyu, 2019, 30000),
                new Transaction(kyu, 2020, 12000),
                new Transaction(ming, 2020, 40000),
                new Transaction(ming, 2020, 7100),
                new Transaction(hyuk, 2019, 5900),
                new Transaction(hwan, 2020, 4900));
    }

    public void quiz1() {
        // 2020년에 일어난 모든 거래내역을 찾아 거래값을 기준으로 오름차순 정렬
        List<Transaction> result = transactions.stream()
                .filter(t -> t.getYear() == 2020)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }

    public void quiz2() {
        // 거래내역이 있는 거래자가 근무하는 모든 도시를 중복 없이 나열
        List<String> result = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    public void quiz3() {
        // 서울에서 근무하는 모든 거래자를 찾아서 이름순서대로 정렬하라.
        List<Trader> result = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(t -> "Seoul".equals(t.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("result = " + result);
    }

    public void quiz4() {
        // 모든 거래자의 이름을 순서대로 정렬하라
        List<String> result = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("result = " + result);
    }

    public void quiz5() {
        // 부산에 거래자가 있는지 확인하라
        boolean exists = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .anyMatch("Busan"::equals);
        System.out.println("exists = " + exists);
    }

    public void quiz6() {
        // 서울에 거주하는 거래자의 모든 거래 내역을 구하라.
        List<Transaction> result = transactions.stream()
                .filter(t -> "Seoul".equals(t.getTrader().getCity()))
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    public void quiz7() {
        // 모든 거래 내역중에서 최댓값과 최솟값을 구하라. 단, 최댓값은 reduce를 이용하고 최솟값은 stream의 min()을 이용하라.
        int max = transactions.stream()
                .map(Transaction::getValue)
                .mapToInt(i -> i)
                .reduce(Integer::max)
                .orElseThrow();

        int min = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .orElseThrow()
                .getValue();
        System.out.println("max = " + max);
        System.out.println("min = " + min);
    }
}

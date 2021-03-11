package com.wordbe.java8to11;

import com.wordbe.java8to11.person.Cap;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) {
        int size = 2000;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println("Serial sorting: " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println("Parallel sorting: " + (System.nanoTime() - start));
    }

    static class BlueCap<@Cap T> {
        private @Cap String name;

        public static <@Cap C> void print(@Cap C c) {
            System.out.println(c);
        }
    }

    private static void completableFutureExample2() throws InterruptedException, ExecutionException {
        boolean throwError = true;

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "error!!";
            }
            return result;
        });

        System.out.println(hello.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("world " + Thread.currentThread().getName());
            return message + " world";
        });
    }

    private static void completableFutureExample() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, executorService);
        future.get();
        executorService.shutdown();
    }

    private static void callableAndFutureExample() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> red = () -> {
            Thread.sleep(2000l);
            return "red";
        };

        Callable<String> orange = () -> {
            Thread.sleep(3000l);
            return "orange";
        };

        Callable<String> blue = () -> {
            Thread.sleep(1000l);
            return "blue";
        };

        // invokeAll
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(red, orange, blue));
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }

        // invokeAny
        String fastestCallable = executorService.invokeAny(Arrays.asList(red, orange, blue));
        System.out.println(fastestCallable);
        executorService.shutdown();
    }

    private static void executorExample() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.schedule(getRunnable("one"), 3, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(getRunnable("two"), 1, 2, TimeUnit.SECONDS);
//        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }

    private static void threadExample() throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        // Main 쓰레드
        System.out.println(Thread.currentThread().getName()); // main

        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("쓰레드: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello: " + Thread.currentThread().getName()); // main
        thread.join();
        System.out.println(thread + "is finished!");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}

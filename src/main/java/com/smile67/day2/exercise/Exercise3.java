package com.smile67.day2.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exercise3 {

    public static void main(String[] args) {
//        List<Integer> result = filter(List.of(1, 2, 3, 4, 5, 6), (Integer number) -> (number & 1) == 1);
//        System.out.println(result);

//        List<String> map = map(List.of(1, 2, 3, 4, 5, 6), number -> String.valueOf(number));
//        System.out.println("map = " + map);

//        consume(List.of(1, 2, 3, 4, 5, 6), number -> System.out.print(number));
        List<Integer> supply = supply(5, () -> ThreadLocalRandom.current().nextInt());
        System.out.println("supply = " + supply);
    }

    static List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer number : list) {
            // 筛选：判断是否是偶数，但以后可能改变筛选规则
            if (predicate.test(number)) {
                result.add(number);
            }
        }
        return result;

        /*
            判断逻辑：(number & 1) == 0
            函数对象：(Integer number) -> (number & 1) == 0
         */
    }

    static List<String> map(List<Integer> list, Function<Integer, String> function) {
        List<String> result = new ArrayList<>();
        for (Integer number : list) {
            // 转换：将数字转为字符串，但以后可能改变转换规则
            result.add(function.apply(number));
        }
        return result;
    }
    /*
        逻辑部分：String.valueOf(number)
        函数对象：number -> String.valueOf(number)
     */

    static void consume(List<Integer> list, Consumer<Integer> consumer) {
        for (Integer number : list) {
            // 消费：打印，但以后可能改变消费规则
            consumer.accept(number);
        }
    }
    /*
        逻辑部分：System.out.println(number);
        函数对象：(Integer number) -> System.out.println(number);
     */

    static List<Integer> supply(int count, Supplier<Integer> supplier) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // 生成：随机数，但以后可能改变生成规则
            result.add(supplier.get());
        }
        return result;
    }
    /*
        逻辑部分：ThreadLocalRandom.current().nextInt()
        函数对象：() -> ThreadLocalRandom.current().nextInt()
     */
}

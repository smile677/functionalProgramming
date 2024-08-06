package com.smile67.day2.methodref;

import java.util.function.Consumer;
import java.util.function.Function;

public class MethodRef7 {
    public static void main(String[] args) {
        // Consumer: 函数式接口，接受一个参数，没有返回值
        Consumer<Object> x = MethodRef7::print1;
        Function<Object, Integer> y = MethodRef7::print2;
        // 特例 有返回值的函数对象可以配合没有返回值的函数式接口一起用但是参数要保持一致,这样说白了就是不关心返回的结果
        Consumer<Object> z = MethodRef7::print2;
    }

    static void print1(Object obj) {
        System.out.println(obj);
    }

    static int print2(Object obj) {
        System.out.println(obj);
        return 1;
    }
}

package com.smile67.day2.exercise;

public class Exercise5 {
    record Color(Integer red, Integer green, Integer blue) { }

    // 如果想用 `Color::new` 来构造 Color 对象，还应当补充哪些代码

    public static void main(String[] args) {
        // 创建Color对象的逻辑封装在了函数对象里面
        TernaryFunction lambda = Color::new; // (Integer, Integer, Integer) -> Color
        // 真正创建Color对象的逻辑
        Color white = lambda.create(255, 255, 255);
        System.out.println(white);
    }

    @FunctionalInterface
    interface TernaryFunction { // TernaryFunction: 三元函数
        Color create(Integer red, Integer green, Integer blue);
    }
}

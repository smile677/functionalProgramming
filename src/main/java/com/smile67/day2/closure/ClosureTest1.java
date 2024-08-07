package com.smile67.day2.closure;

public class ClosureTest1 {

    @FunctionalInterface
    interface Lambda {
        int op(int y);
    }

    static void highOrder(Lambda lambda) {
        System.out.println(lambda.op(1));
    }

    public static void main(String[] args) {
        /*
            函数对象 (int y) -> x + y 与它外部的变量 x 形成了闭包

            effective final: 变量虽然没有用 final 修饰符，但是它的行为必须和加了 final 修饰符的一致
         */
//        int x = 10; // 引用变量的内部可能还是会变
//        highOrder((int y) -> x + y);

        // 函数对象可以与可变状态的变量组成闭包
        Student stu = new Student(20);
        // 函数的不可变性被破坏了: 因为他和可变对象一起使用了--->违背了函数式编程
        Lambda lambda = y -> y + stu.d; // 只做了最外层的检测
        highOrder(lambda); // 21

        stu.d = 40;
        highOrder(lambda); // 41
    }

    static class Student {
        int d;

        public Student(int d) {
            this.d = d;
        }
    }

    // 静态变量
    static int a = 1;
    // 成员变量
    int b = 2;

    public /*static*/ void test(int c/*局部变量*/) { // 静态方法中不能使用成员变量
        // 函数对象的逻辑部分用到了外部变量，可以与外部的变量形成闭包
        // 闭包的限制条件: 变量必须是final或者是 effectively final的 即变量不能被重新赋值了--->根本原因是要保证函数的不变性
        highOrder(y -> a + y);
        highOrder(y -> b + y);
        highOrder(y -> c + y);
    }
}

package com.smile67.day2.currying;

/**
 * 把具有多个参数的函数对象，变成一系列只有一个参数的函数对象的过程：柯里化
 */
public class Carrying0Test {

    @FunctionalInterface
    interface F2 {
        int op(int a, int b);
    }

    @FunctionalInterface
    interface Fa {
        Fb op(int a);
    }

    @FunctionalInterface
    interface Fb {
        int op(int b);
    }

    public static void main(String[] args) {
        // 两个参数的函数对象
        F2 f2 = (a, b) -> a + b;
        System.out.println(f2.op(10, 20));

        /* 需求改造
            (a) -> 返回另一个函数对象
                    (b) -> a+b
         */
        //第一个函数对象返回的结果： (b) -> a + b;
        //第二个函数对象的返回结果： a + b;
        Fa fa = (a) -> (b) -> a + b; // 后面的函数对象用到了前面的参数a
        Fb fb = fa.op(10);
        int r = fb.op(20);
        System.out.println(r);
    }

}

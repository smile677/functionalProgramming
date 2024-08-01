package com.smile67.day1;

public class Sample4 {

    // 普通函数
    static int add(int a, int b) {
        return a + b;
    }

    // 函数对象的类型是接口类型
    interface Lambda {
        int calculate(int a, int b);
    }

    // 函数化为对象
    static Lambda add = (a, b) -> a + b;

    /*
     * 前者：是纯粹的一条 两数加法 规则，它的位置是固定的，要使用它，需要通过 Sample4.add 找到它，然后执行
     * 而后：者（add 对象）就像长了腿，它（函数对象）的**位置是可以变化**的，想去哪里就去哪里，哪里要用到这条加法规则，把它传递过去
     * 接口的目的是为了将来用它来执行**函数对象**，此接口中只能有一个方法定义
     */

    public static void main(String[] args) {
        System.out.println(Sample4.add(3, 4));
        // 通过对象add调用接口类型来执行加法规则
        System.out.println(add.calculate(5, 6));
    }

}

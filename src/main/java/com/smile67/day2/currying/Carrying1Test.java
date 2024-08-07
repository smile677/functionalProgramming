package com.smile67.day2.currying;

import java.util.ArrayList;
import java.util.List;

public class Carrying1Test {

    @FunctionalInterface// 函数式接口
    interface Fa {
        Fb op(List<Integer> a);
    }

    @FunctionalInterface
    interface Fb {
        Fc op(List<Integer> b);
    }

    @FunctionalInterface
    interface Fc {
        List<Integer> op(List<Integer> c);
    }

    /*
        目标：把三份数据合在一起，逻辑既定，但数据不能一次得到
        a -> 函数对象1(返回的函数对象)  --->相当于把a记录下来了
                b -> 函数对象(返回的函数对象)
                     c -> 完成合并(返回的数据)
     */
    static Fb step1() {
        List<Integer> x = List.of(1, 2, 3);
        // 合并数据的逻辑一开始就定义了，但数据是缺失的，所以需要后续的步骤去补全数据
        Fa fa = a -> b -> c -> {
            List<Integer> list = new ArrayList<>();
            list.addAll(a);
            list.addAll(b);
            list.addAll(c);
            return list;
        };
        return fa.op(x);
    }

    static Fc step2(Fb fb) {
        // 将缺失的数据准备好
        List<Integer> y = List.of(4, 5, 6);
        return fb.op(y);
    }

    static void step3(Fc fc) {
        List<Integer> z = List.of(7, 8, 9);
        List<Integer> result = fc.op(z);
        System.out.println(result);
    }

    public static void main(String[] args) {
        step3(step2(step1()));
    }
}

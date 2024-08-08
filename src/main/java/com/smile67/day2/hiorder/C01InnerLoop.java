package com.smile67.day2.hiorder;

import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class C01InnerLoop {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);
        // 需求：逆序遍历集合，只想负责元素处理，不改变集合
        hiOrder(list, (value) -> System.out.println(value));
    }

    // 内循环: 循环在高阶函数的内部执行的
    public static <T> void hiOrder(List<T> list, Consumer<T> consumer) {
        // for 遍历会遇到性能瓶颈（数组时不会链表时就会）
        ListIterator<T> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            T value = iterator.previous();
            consumer.accept(value);
        }
    }
}

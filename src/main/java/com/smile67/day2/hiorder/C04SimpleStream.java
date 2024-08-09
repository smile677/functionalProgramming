package com.smile67.day2.hiorder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

public class C04SimpleStream<T> {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 1, 2, 3);
//        C04SimpleStream.of(list)
//                .filter(x -> (x & 1) == 1)
//                .map(x -> x * x)
//                .forEach(System.out::println);
//        System.out.println(C04SimpleStream.of(list).reduce(0, (a, b) -> a + b));
//        System.out.println(C04SimpleStream.of(list).reduce(Integer.MIN_VALUE, Integer::max));
//        System.out.println(C04SimpleStream.of(list).reduce(Integer.MAX_VALUE, Integer::min));
        // 收集字符串
        HashSet<Object> collect1 = C04SimpleStream.of(list).collect(() -> new HashSet<>(), (set, t) -> set.add(t));
        // () -> new HashSet<>() HashSet::new
        // (set, t) -> set.add(t)) HashSet::add
        System.out.println("collect1 = " + collect1);

        StringBuilder collect2 = C04SimpleStream.of(list).collect(StringBuilder::new, (sb, t) -> sb.append(t));
        System.out.println("collect2 = " + collect2);

        // 不是所有的方法都能有方法引用的
        StringJoiner collection3 = C04SimpleStream
                .of(list)
//                .collect(() -> new StringJoiner("-"), (joiner, t) -> joiner.add(String.valueOf(t)));
                .map(t -> String.valueOf(t))
                .collect(() -> new StringJoiner("-"), StringJoiner::add);
        //(StringJoiner, Integer) -> void
        //(StringJoiner, CharSequence) -> void
        System.out.println("collection3 = " + collection3);


        /*
            key         value
            1           2
            2           2
            3           2
            4           1
            5           1
         */
        HashMap<Integer, Integer> collect4 = C04SimpleStream
                .of(list)
                .collect(HashMap::new, (HashMap<Integer, Integer> map, Integer t) -> {
                    if (!map.containsKey(t)) {
                        map.put(t, 1);
                    } else {
                        Integer v = map.get(t);
                        map.put(t, v + 1);
                    }
                });
        System.out.println("collect4 = " + collect4); // {1=2, 2=2, 3=2, 4=1, 5=1}

        // 简洁化
        /*
            如果 key 在 map 中不存在，将 key 连同新生成的 value 值存入 map, 并返回 value
            如果 key 在 map 中存在，会返回此 key 上次的 value 值

            1, 2, 3, 4, 5, 1, 2, 3

            key     value
            1       AtomicInteger(2)
            2       AtomicInteger(2)
            3       AtomicInteger(2)
            4       AtomicInteger(1)
            5       AtomicInteger(1)
         */
        HashMap<Integer, AtomicInteger> collect5 = C04SimpleStream
                .of(list)
                .collect(HashMap::new, (map, t) -> map.computeIfAbsent(t, k -> new AtomicInteger()).getAndIncrement());
        System.out.println("collect5 = " + collect5); // {1=2, 2=2, 3=2, 4=1, 5=1}

    }

    // C代表容器 supplier 用来创建容器
    public <C> C collect(Supplier<C> supplier, BiConsumer<C, T> consumer) {
        C c = supplier.get(); // 创建了容器
        for (T t : collection) {
            consumer.accept(c, t); // 向容器中添加元素
        }
        // 添加方法 set add  map put StringBuilder append
        return c;
    }


    // o 代表 p 的初始值
    public T reduce(T o, BinaryOperator<T> operator) {
        T p = o;
        for (T t : collection) {
            p = operator.apply(p, t);
        }
        return p;
    }

    public C04SimpleStream<T> filter(Predicate<T> predicate) {
        List<T> res = new ArrayList<>();
        for (T t : collection) {
            if (predicate.test(t)) {
                res.add(t);
            }
        }
        return new C04SimpleStream<>(res);
    }

    public <U> C04SimpleStream<U> map(Function<T, U> function) {
        List<U> res = new ArrayList<>();
        for (T t : collection) {
            U u = function.apply(t);
            res.add(u);
        }
        return new C04SimpleStream<>(res);
    }

    public void forEach(Consumer<T> consumer) {
        for (T t : collection) {
            consumer.accept(t);
        }
    }

    // <T>: 方法泛型
    // 类上的泛型不能配合静态方法使用
    // 不支持并发不支持懒惰执行
    public static <T> C04SimpleStream<T> of(Collection<T> collection) { // 工厂方法
        return new C04SimpleStream<>(collection);
    }

    private Collection<T> collection;

    private C04SimpleStream(Collection<T> collection) {
        this.collection = collection;
    }
}

package com.smile67.day2.closure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClosureTest2 {

    // 闭包作用：给函数对象提供参数以外的数据
    public static void main(String[] args) throws IOException {
        // 创建 10 个任务对象，并且每个任务对象给一个任务编号
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 闭包变量
            int k = i + 1; // 如何将一个变化的量在循环内变成多个不变的（effective final）量
            Runnable task = () -> System.out.println(Thread.currentThread() + ":执行任务" + k);
            list.add(task);
        }
        // 守护线程
        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        for (Runnable task : list) {
            service.submit(task);
        }
        System.in.read();// 让主线程不要停止
    }
}

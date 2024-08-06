package com.smile67.day2.methodref;

import java.util.stream.Stream;

public class MethodRef5 {
    public static void main(String[] args) {
        Util util = new UtilExt();
        util.hiOrder(Stream.of(
                new Student("张无忌", "男"),
                new Student("周芷若", "女"),
                new Student("宋青书", "男")
        ));
    }

    record Student(String name, String sex) {
    }

    static class Util {
        private boolean isMale(Student stu) {
            return stu.sex().equals("男");
        }

        boolean isFemale(Student stu) {
            return stu.sex().equals("女");
        }

        // 过滤男性学生并打印
        void hiOrder(Stream<Student> stream) {
            stream
//                    .filter(stu->this.isMale(stu))
                    .filter(this::isMale) // this::非静态方法
                    .forEach(System.out::println);
        }
    }

    static class UtilExt extends Util {


        // 过滤女性学生并打印
        @Override
        void hiOrder(Stream<Student> stream) {
            stream.filter(super::isFemale)
                    .forEach(System.out::println);
        }
    }
}

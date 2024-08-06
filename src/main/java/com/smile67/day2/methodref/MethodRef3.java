package com.smile67.day2.methodref;

import java.util.stream.Stream;

public class MethodRef3 {

    static class Util {
        public boolean isMale(Student stu) {
            return stu.sex().equals("男");
        }

        public String xyz(Student stu) {
            return stu.name();
        }
    }

    public static void main(String[] args) {
        Util util = new Util();
        Stream.of(
                        new Student("张无忌", "男"),
                        new Student("周芷若", "女"),
                        new Student("宋青书", "男")
                )
                .filter(util::isMale)
                //.map(stu->stu.name()) // lambda 表达式
                //.map(util::xyz) // 对象::非静态方法
                .map(Student::name) // 类名::非静态方法 ---> 更简单一些
                .forEach(System.out::println);
    }
    /*
        对象::非静态方法
        对象引用: util::xyz
        Lambda对象: (stu) -> util.isMale(stu)
        Lambda对象: (stu) -> util.xyz(stu)
     */

    /*
        对象::非静态方法
        对象引用: System.out::println
        Lambda对象: stu -> System.out.println(stu)
     */

    /**
     * record类把方法简化了
     *
     * @param name
     * @param sex
     */
    record Student(String name, String sex) {
        public String name() {
            return this.name;
        }
        /*
            Student::name
            stu -> stu.name()
         */

        public String sex() {
            return this.sex;
        }
    }
}

package com.smile67.day1;

import java.util.ArrayList;
import java.util.List;

// 函数对象好处1：行为参数化
public class Sample6 {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("张无忌", 18, "男"),
                new Student("杨不悔", 16, "女"),
                new Student("周芷若", 19, "女"),
                new Student("宋青书", 20, "男")
        );

        /*
            需求1：筛选男性学生
         */
//        System.out.println(filter1(students));
        System.out.println(filter0(students, student -> student.sex.equals("男")));

        /*
            需求2：筛选18岁以下学生
         */
        System.out.println(filter0(students, student -> student.age < 18));

        System.out.println(filter0(students, student -> student.sex.equals("女")));
    }
    // 定义接口的目的：方便通过接口中的一个抽象的方法来间接的执行函数对象里面的判断的逻辑
    interface Lambda {
        boolean test(Student student);
    }

    /**
     * 行为参数化：将逻辑部分作为参数传递给方法
     * @param students
     * @param lambda
     * @return
     */
    static List<Student> filter0(List<Student> students, Lambda lambda) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (lambda.test(student)) {
                result.add(student);
            }
        }
        return result;
    }

    // 参数 -> 逻辑部分   student -> student.sex.equals("男")
    static List<Student> filter1(List<Student> students) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.sex.equals("男")) {
                result.add(student);
            }
        }
        return result;
    }

    // student -> student.age < 18
    static List<Student> filter2(List<Student> students) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.age < 18) {
                result.add(student);
            }
        }
        return result;
    }

    static class Student {
        private String name;
        private int age;
        private String sex;

        public Student(String name, int age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public String getSex() {
            return sex;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }
}

package com.justbon.java8.lambda;

import com.justbon.util.Student;

import java.math.BigDecimal;
import java.util.function.*;

/**
 * @author ganli
 * @version 1.0
 * @file Test.java
 * @Modified By：
 * @date 2019-11-22 上午11:05
 * @description
 */
public class Test {
    public static void main(String[] args) {
        Predicate<Integer> predicate = x -> x > 185;
        Student student = new Student();
        student.setName("sss");
        student.setSex("1");
        Consumer<String> consumer = System.out::println;
        consumer.accept("命运由我不由天");

        Function<Student, String> function = Student::getName;
        String name = function.apply(student);
        System.out.println(name);

        Supplier<Integer> supplier =
                () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        Boolean apply2 = unaryOperator.apply(true);
        System.out.println(apply2);

        BinaryOperator<Integer> operator = (x, y) -> x * y;
        Integer integer = operator.apply(2, 3);
        System.out.println(integer);

        test(() -> "我是一个演示的函数式接口");
    }

    /**
     * 演示自定义函数式接口使用
     *
     * @param worker
     */
    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);
    }

    public interface Worker {
        String work();
    }
}

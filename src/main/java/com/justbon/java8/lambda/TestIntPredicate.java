package com.justbon.java8.lambda;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * 我们介绍了三个泛型函数式接口：Predicate<T>、Consumer<T>和Function<T,R>。还
 * 有些函数式接口专为某些类型而设计
 *
 * Java 8为我们前面所说的函数式接口带来了一个专门的版本，以便在输入和输出都是原始类
 * 型时避免自动装箱的操作。比如，在下面的代码中，使用IntPredicate就避免了对值1000进行
 * 装箱操作，但要是用Predicate<Integer>就会把参数1000装箱到一个Integer对象中
 *
 *
 * 一般来说，针对专门的输入参数类型的函数式接口的名称都要加上对应的原始类型前缀，比
 * DoublePredicate、IntConsumer、LongBinaryOperator、IntFunction等。Function
 * 口还有针对输出参数类型的变种：ToIntFunction<T>、IntToDoubleFunction等
 *
 */
public class TestIntPredicate {

    public static void main(String[] args) {
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;//true（无装箱）
        evenNumbers.test(1000);

        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
        //false（装箱）
        //虽然 Java有一个自动装箱机制来帮助程序员执行这一任务，但是 性能特别低
        oddNumbers.test(1000);
    }
}

package com.justbon.java8.lambda.methodrefrence;

import java.util.function.Function;

/***
 *
 * 方法引用
 * lambda表达式还有两种简化代码的手段，它们是方法引用、构造引用。
 *
 * 方法引用是什么呢？如果我们要实现接口的方法与另一个方法A类似，
 * （这里的类似是指参数类型与返回值部分相同），
 * 我们直接声明A方法即可。也就是，不再使用lambda表达式的标准形式，
 * 改用高级形式。
 * 无论是标准形式还是高级形式，都是lambda表达式的一种表现形式。
 *
 */
public class TestMethod {
    public static void main(String[] args) {
        Function function1 = (x) -> x;
        Function function2 = String::valueOf;
        /**
         *
         * 对比Function接口的抽象方法与String的value方法，可以看到它们是类似的。
         *
         *     R apply(T t);
         *     public static String valueOf(Object obj) {
         *         return (obj == null) ? "null" : obj.toString();
         *     }
         *
         *     方法引用的语法：
         *
         * 对象::实例方法
         * 类::静态方法
         * 类::实例方法
         *
         * 前两个很容易理解，相当于对象调用实例方法，类调用静态方法一样。只是第三个需要特殊说明。
         *
         *
         * 当出现如下这种情况时：
         *
         * Compare<Boolean> c = (a, b) -> a.equals(b);
         * 用lambda表达式实现Compare接口的抽象方法，并且方法体只有一行，且该行代码为参数1调用方法传入参数2。此时，就可以简化为下面这种形式：
         *
         * Compare<Boolean> c = String::equals;
         * 也就是“类::实例方法”的形式。
         */
    }
}

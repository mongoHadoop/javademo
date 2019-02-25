package com.justbon.java8.lambda.methodrefrence;

import java.util.Arrays;
import java.util.List;

public class TestMethod2 {
    public static void main(String[] args) {


        /****
         *
         *
         * 当出现如下这种情况时：
         *
         * Compare<Boolean> c = (a, b) -> a.equals(b);
         * 用lambda表达式实现Compare接口的抽象方法，并且方法体只有一行，且该行代码为参数1调用方法传入参数2。此时，就可以简化为下面这种形式：
         *
         * Compare<Boolean> c = String::equals;
         * 也就是“类::实例方法”的形式。
         *
         *
         */


        //Lambda表达式的签名与Comparator的函数描述符兼容。
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        //请注意，编译器会进行一种与Lambda表达式类似的类型检查过程，
        //来确定对于给定的函数 式接口，这个方法引用是否有效：方法引用的签名必须和上下文类型匹配。
        List<String> str2 = Arrays.asList("a","b","A","B");
        str2.sort(String::compareToIgnoreCase);



    }
}

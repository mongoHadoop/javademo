package com.justbon.java8.methodrefrance;

import java.util.Arrays;
import java.util.List;

public class TestMethod {
    public static void main(String[] args) {
        //Lambda表达式的签名与Comparator的函数描述符兼容。
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        //请注意，编译器会进行一种与Lambda表达式类似的类型检查过程，
        //来确定对于给定的函数 式接口，这个方法引用是否有效：方法引用的签名必须和上下文类型匹配。
        List<String> str2 = Arrays.asList("a","b","A","B");
        str2.sort(String::compareToIgnoreCase);

    }
}

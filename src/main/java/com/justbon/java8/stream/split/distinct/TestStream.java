package com.justbon.java8.stream.split.distinct;

import java.util.Arrays;
import java.util.List;

public class TestStream {

    /***
     *  通过 equel .
     *  hashcode判断 对象是否重复
     *
     * distinct
     * @param args
     */
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }
}

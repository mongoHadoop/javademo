package com.justbon.java8.stream.reduce;

import java.util.Arrays;
import java.util.List;

public class TestStream {

    /***
     *
     * reduce 规约
     * @param args
     */
    public static void main(String[] args) {




        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        /**
         * reduce接受两个参数：
         一个初始值，这里是0；
         一个BinaryOperator<T>来将两个元素结合起来产生一个新值，这里我们用的是
         lambda (a, b) -> a + b。
         *
         **/

        int sum = numbers.stream().reduce(0, (a, b) -> a + b);

        /**
         *
         *
         你可以使用方法引用让这段代码更简洁

         在Java 8中，Integer类现在有了一个静态的sum
         方法来对两个数求和，这恰好是我们想要的，用不着反复用Lambda写同一段代码了：
         **/

        int sum2 = numbers.stream().reduce(0, Integer::sum);


    }
}

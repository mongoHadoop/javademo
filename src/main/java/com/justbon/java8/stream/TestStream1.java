package com.justbon.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * 1.如何理解Stream
 * Stream 不是集合元素，它不是数据结构并不保存数据，它是有关算法和计算的，它更像一个高级版本的 Iterator。简单来说，它的作用就是通过一系列操作将数据源（集合、数组）转化为想要的结果。
 *2.Stream特点
 * Stream 是不会存储元素的。
 *
 * Stream 不会改变原对象，相反，他们会返回一个持有结果的新Stream。
 *
 * Stream 操作是延迟执行的。意味着它们会等到需要结果的时候才执行。
 */
//生成Stream的方式
public class TestStream1 {
    public static void main(String[] args) {
//Collection系的 stream() 和 parallelStream();
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Stream<String> stringStream = list.parallelStream();

//通过Arrays
        Stream<String> stream1 = Arrays.stream(new String[10]);

//通过Stream
        Stream<Integer> stream2 = Stream.of(1, 2, 3);

//无限流
//迭代
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        iterate.limit(10).forEach(System.out::println);

//生成
        Stream<Double> generate = Stream.generate(() -> Math.random());
        generate.forEach(System.out::println);
    }
}

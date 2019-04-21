package com.justbon.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestReduce {
    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

    /***
     *
     * 你可以像下面这样对流中所有的元素求和
     *
     * reduce接受两个参数：
     *  一个初始值，这里是0；
     *  一个BinaryOperator<T>来将两个元素结合起来产生一个新值，这里我们用的是
     * lambda (a, b) -> a + b。
     */
    @Test
    public void streamReduceOPT()
    {
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        System.out.println(sum2);
        //相乘
        int product =numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);
    }
    /***
     *
     * 原来，只要用归约就可以计算最大值和最小值了！让我们来看看如何利用刚刚学到的reduce
     * 来计算流中最大或最小
     */

    @Test
    public void streamReduceMaxMin()
    {
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.ifPresent(e-> System.out.println(e));
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(e-> System.out.println(e));
    }
}

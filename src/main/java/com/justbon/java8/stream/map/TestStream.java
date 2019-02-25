package com.justbon.java8.stream.map;

import com.justbon.java8.stream.pojo.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * map 后会返回一个新的对象 即用funciton接口函数
 * 对流中的每一个元素映射一个处理函数
 *
 * 流支持map方法，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映
 * 射成一个新的元素
 */
public class TestStream {

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
/**
 * 因为getName方法返回一个String，所以map方法输出的流的类型就是Stream<String>
 */
        System.out.println(dishNames);

        /**
         *
         * 让我们看一个稍微不同的例子来巩固一下对map的理解
         *给定一个单词列表，你想要返回另
         * 一个列表，显示每个单词中有几个字母。怎么做呢？你需要对列表中的每个元素应用一个函数。
         * 这听起来正好该用map方法去做！应用的函数应该接受一个单词，并返回其长度。
         *
         */


        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());


        System.out.println(wordLengths);

    }
}

package com.justbon.java8.stream;

import com.justbon.java8.stream.pojo.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 *
 * .Stream的中间操作
 * 多个中间操作连接而成为流水线，流水线不遇到终止操作是不触发任何处理的，所为又称为“惰性求值”。
 *
 */
public class TestStream2 {
    public static void main(String[] args) {
      /*  List<Dish> list = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        list.stream()
                .map(s -> s.getCalories() + 1)  //映射
                .flatMap(s -> Stream.of(s))
//和map差不多，但返回类型为Stream，类似list.add()和list.addAll()的区别
                .filter(s -> s< 1000)    //过滤
                .limit(5)   //限制
                .skip(1)    //跳过
                .distinct() //去重
                .sorted()   //自然排序
                .sorted(Integer::compareTo);

        *//**
         * //Stream的终止操作
         *//*

        list.stream().allMatch((x) -> x.getCalories() == 555); // 检查是否匹配所有元素
        list.stream().anyMatch(((x) -> x.getCalories()>600)); // 检查是否至少匹配一个元素
        list.stream().noneMatch((x) -> x.getCalories()>500); //检查是否没有匹配所有元素
        list.stream().findFirst(); // 返回第一个元素
        list.stream().findAny(); // 返回当前流中的任意一个元素
        list.stream().count(); // 返回流中元素的总个数
        list.stream().forEach(System.out::println); //内部迭代
        list.stream().max(Integer::compareTo); // 返回流中最大值
        Optional<Integer> min = list.stream().min(Integer::compareTo);//返回流中最小值
        System.out.println("min "+min.get());
        *//**
         * reduce （归约）：将流中元素反复结合起来得到一个值
         *
         **//*

        Integer reduce = list.stream()
                .map(s -> (s.getCalories() + 1))
                .reduce(0, (x, y) -> x + y);
        //归约：0为第一个参数x的默认值，x是计算后的返回值，y为每一项的值。
        System.out.println(reduce);

        Optional<Integer> reduce1 = list.stream()
                .map(s -> (s.getCalories() + 1))
                .reduce((x, y) -> x + y);
        // x是计算后的返回值，默认为第一项的值，y为其后每一项的值。
        System.out.println(reduce);


        //collect（收集）：将流转换为其他形式。需要Collectors类的一些方法。

        //转集合
        Set<Dish> collect = list.stream()
                .collect(Collectors.toSet());

        List<Dish> collect2 = list.stream()
                .collect(Collectors.toList());

        HashSet<Dish> collect1 = list.stream()
                .collect(Collectors.toCollection(HashSet::new));

        //分组 {group=[444, 555, 666, 777, 555]}
        Map<String, List<Dish>> collect3 = list.stream()
                .collect(Collectors.groupingBy((x) -> "group"));//将返回值相同的进行分组
        System.out.println(collect3);

        //多级分组 {group={777=[777], 666=[666], 555=[555, 555], 444=[444]}}
        Map<String, Map<Dish, List<Dish>>> collect4 = list.stream()
                .collect(Collectors.groupingBy((x) -> "group", Collectors.groupingBy((x) -> x)));
        System.out.println(collect4);

        //分区 {false=[444], true=[555, 666, 777, 555]}
        Map<Boolean, List<Dish>> collect5 = list.stream()
                .collect(Collectors.partitioningBy((x) -> x.getCalories() > 500));
        System.out.println(collect5);

        //汇总
        DoubleSummaryStatistics collect6 = list.stream()
                .collect(Collectors.summarizingDouble((x) -> x.getCalories()));
        System.out.println(collect6.getMax());
        System.out.println(collect6.getCount());

        //拼接 444,555,666,777,555
        String collect7 = list.stream()
                .map(s -> s.toString())
                .collect(Collectors.joining(","));
        System.out.println(collect7);

        //最大值
        Optional<Dish> integer = list.stream()
                .collect(Collectors.maxBy(Integer::compare));
        System.out.println(integer.get());
*/

    }
}

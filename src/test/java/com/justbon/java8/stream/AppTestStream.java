package com.justbon.java8.stream;

import com.alibaba.fastjson.JSONObject;
import com.justbon.java8.stream.pojo.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class AppTestStream {

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

    @Test
    public void streamMapFilter()
    {

        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName) .limit(3) .collect(toList());

        System.out.println(threeHighCaloricDishNames);

    }

    @Test
    public void streamMapFilterLimit()
    {

        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getName) .limit(3) .collect(toList());

        System.out.println(threeHighCaloricDishNames);

    }
    /***
     * 流还支持一个叫作distinct的方法，它会返回一个元素各异（根据流所生成元素的
     * hashCode和equals方法实现）的流
     *
     */
    @Test
    public void streamFilterDistinct()
    {


        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);


    }
    /**
     *
     * 截短流
     */
    @Test
    public void streamFilterLimit()
    {

        List<String> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .map(Dish::getName)
                .collect(toList());

        System.out.println(dishes);
    }
    /**
     * 跳过元素
     * 流还支持skip(n)方法，返回一个扔掉了前n个元素的流
     */
    @Test
    public void streamMapSkip()
    {

        List<String> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(1)
                .map(Dish::getName)
                .collect(toList());

        System.out.println(dishes);
    }
    /***
     *
     * 对流中每一个元素应用函数 。Stream API也通过map和flatMap方法提供了类似的工具
     *
     * 因为getName方法返回一个String，所以map方法输出的流的类型就是Stream<String>
     *
     *
     *
     */

    @Test
    public void streamMap()
    {

        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());

        System.out.println(dishNames);
    }
    /**
     *
     * 让我们看一个稍微不同的例子来巩固一下对map的理解。给定一个单词列表，你想要返回另
     * 一个列表，显示每个单词中有几个字母。怎么做呢？你需要对列表中的每个元素应用一个函数。
     * 这听起来正好该用map方法去做！应用的函数应该接受一个单词，并返回其长度。你可以像下面
     * 这样，给map传递一个方法引用String::length来解决这个问题：
     */
    @Test
    public void streamMap2()
    {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Map> word = words.stream()
                .map(e->{
                    Map bean = new HashMap();
                    bean.put(e,e.length());
                    return bean;
                })
                .collect(toList());

        System.out.println(JSONObject.toJSONString(word));

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());

        System.out.println(JSONObject.toJSONString(dishNameLengths));
    }
    /***
     * 流的扁平化
     *
     * 你已经看到如何使用map方法返回列表中每个单词的长度了。让我们拓展一下：对于一张单
     * 词表， 如何返回一张列表， 列出里面各不相同的字符呢？ 例如， 给定单词列表
     * ["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"]。
     *
     *
     *
     * 你可能会认为这很容易，你可以把每个单词映射成一张字符表，然后调用distinct来过滤
     * 重复的字符。第一个版本可能是这样的：
     * words.stream()
     * .map(word -> word.split(""))
     * .distinct()
     * .collect(toList());
     * 这个方法的问题在于，传递给map方法的Lambda为每个单词返回了一个String[]（String
     * 列表）。因此， map 返回的流实际上是Stream<String[]> 类型的。你真正想要的是用
     * Stream<String>来表示一个字符流。图
     *
     * 一言以蔽之，flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接
     * 起来成为一个流。
     *
     */

    @Test
    public void streamMapflatMap()
    {
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        List<String> uniqueCharacters =
                streamOfwords
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(uniqueCharacters));
    }

}

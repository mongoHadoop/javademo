package com.justbon.java8.stream;

import com.alibaba.fastjson.JSONObject;
import com.justbon.java8.stream.pojo.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class AppTestStream2 {
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

    /***
     *
     * 检查谓词是否至少匹配一个元素
     * anyMatch
     * anyMatch方法返回一个boolean，因此是一个终端操作。
     */
    @Test
    public void streamAnyMatch()
    {

        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

    }

    /***
     *
     * AllMatch
     * allMatch方法的工作原理和anyMatch类似，但它会看看流中的元素是否都能匹配给定的谓
     * 词。比如，你可以用它来看看菜品是否有利健康（即所有菜的热量都低于1000卡路里）：
     */
    @Test
    public void streamAllMatch()
    {
        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        if(isHealthy){
            System.out.println("The menu isisHealthy !");
        }



    }
    /***
     * noneMatch
     * 和allMatch相对的是noneMatch。它可以确保流中没有任何元素与给定的谓词匹配。比如，
     * 你可以用noneMatch重写前面的例子：
     */
    @Test
    public void streamNoneMatch()
    {
        boolean isHealthy = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        if(isHealthy){
            System.out.println("The menu isisHealthy !");
        }

    }
    /**
     *
     * 查找元素
     * findAny方法将返回当前流中的任意元素。它可以与其他流操作结合使用。比如，你可能想
     * 找到一道素食菜肴。你可以结合使用filter和findAny方法来实现这个查询
     *
     * Optional简介
     * Optional<T>类（java.util.Optional）是一个容器类，代表一个值存在或不存在。在
     * 上面的代码中，findAny可能什么元素都没找到。Java 8的库设计人员引入了Optional<T>，这
     * 样就不用返回众所周知容易出问题的null了。
     *
     * Optional里面几种可以迫使你显式地检查值是否存在或处理值不存在的情形的方法也不错。
     *  isPresent()将在Optional包含值的时候返回true, 否则返回false。
     *  ifPresent(Consumer<T> block)会在值存在的时候执行给定的代码块。我们在第3章
     * 介绍了Consumer函数式接口；它让你传递一个接收T类型参数，并返回void的Lambda
     * 表达式。
     *  T get()会在值存在时返回值，否则抛出一个NoSuchElement异常。
     *  T orElse(T other)会在值存在时返回值，否则返回一个默认值。
     *
     */
    @Test
    public void streamFindAny()
    {
        Optional<Dish> dish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();
        dish.ifPresent(d-> System.out.println(JSONObject.toJSONString(d)));
    }
}

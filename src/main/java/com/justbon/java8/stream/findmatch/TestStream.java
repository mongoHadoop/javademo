package com.justbon.java8.stream.findmatch;

import com.justbon.java8.stream.pojo.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }


//检查谓词是否至少匹配一个元素
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
//检查谓词是否匹配所有元素

        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);

        /**
         *
         *      noneMatch
         *         和allMatch相对的是noneMatch。它可以确保流中没有任何元素与给定的谓词匹配。比如，
         *         你可以用noneMatch重写前面的例子：
         *           anyMatch、allMatch和noneMatch这三个操作都用到了我们所谓的短路，这就是大家熟悉
         *         的Java中&&和||运算符短路在流中的版本
         */

        /***
         *   findAny方法将返回当前流中的任意元素。它可以与其他流操作结合使用。比如，你可能想
         *         找到一道素食菜肴。你可以结合使用filter和findAny方法来实现这个查询：
         *
         *  流水线将在后台进行优化使其只需走一遍，并在利用短路找到结果时立即结束。不过慢着，
         *         代码里面的Optional是个什么玩意儿？
         */

        Optional<Dish> dish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();



    }
}

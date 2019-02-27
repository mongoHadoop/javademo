package com.justbon.java8.stream;

import com.justbon.java8.stream.pojo.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.reducing;

public class TestReducing {


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
     * 事实上，我们已经讨论的所有收集器，都是一个可以用reducing工厂方法定义的归约过程
     * 的特殊情况而已。Collectors.reducing工厂方法是所有这些特殊情况的一般化。
     *
     * 它需要三个参数。
     *  第一个参数是归约操作的起始值，也是流中没有元素时的返回值，所以很显然对于数值
     * 和而言0是一个合适的值。
     *  第二个参数就是你在6.2.2节中使用的函数，将菜肴转换成一个表示其所含热量的int。
     *  第三个参数是一个BinaryOperator，将两个项目累积成一个同类型的值。这里它就是
     * 对两个int求和。
     */
    @Test
    public void testReducing(){
        //可以用reducing方法创建的收集器来计算你菜单的总热量，如下所示：
        int totalCalories = menu.stream().collect(reducing(
                0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories);

    }
}

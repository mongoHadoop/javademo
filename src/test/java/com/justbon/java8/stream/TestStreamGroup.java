package com.justbon.java8.stream;

import com.justbon.java8.stream.pojo.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toList;

public class TestStreamGroup {

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
    public void streamMapCount()
    {

        long howManyDishes = menu.stream().collect(Collectors.counting());

        System.out.println(howManyDishes);

    }

    /**
     * 查找流中的最大值和最小值
     * 假设你想要找出菜单中热量最高的菜。你可以使用两个收集器，Collectors.maxBy和
     * Collectors.minBy，来计算流中的最大或最小值。
     */
    @Test
    public void streamMapTest(){

        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish =
                menu.stream()
                        .collect(maxBy(dishCaloriesComparator));

    }
}

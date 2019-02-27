package com.justbon.java8.stream;

import com.justbon.java8.stream.pojo.Dish;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class TestStreamCount {

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
    public void streamMaxBy(){

        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish =
                menu.stream()
                        .collect(maxBy(dishCaloriesComparator));

        mostCalorieDish.ifPresent(e-> System.out.println(e.getName()));

    }

    /**
     * 汇总
     *
     * Collectors类专门为汇总提供了一个工厂方法：Collectors.summingInt。它可接受一
     * 个把对象映射为求和所需int的函数，并返回一个收集器；该收集器在传递给普通的collect方
     * 法后即执行我们需要的汇总操作。举个例子来说，你可以这样求出菜单列表的总热量：
     * int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
     * 这里的收集过程如图6-2所示。在遍历流时，会把每一道菜都映射为其热量，然后把这个数
     * 字累加到一个累加器（这里的初始值0）。
     * Collectors.summingLong和Collectors.summingDouble方法的作用完全一样，可以用
     * 于求和字段为long或double的情况。
     */

    @Test
    public void streamCount(){
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);
        long totalCalories1 = menu.stream().collect(summingLong(Dish::getCalories));
        System.out.println(totalCalories1);
    }

    /**
     * 但汇总不仅仅是求和；还有Collectors.averagingInt，连同对应的averagingLong和
     * averagingDouble可以计算数值的平均数
     */

    @Test
    public void streamAvg(){
        double avgCalories =
                menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalories);
    }
    /**
     *
     * 不过很多时候，你可能想要得到两个或更多这样的
     * 结果，而且你希望只需一次操作就可以完成。在这种情况下，你可以使用summarizingInt工厂
     * 方法返回的收集器。例如，通过一次summarizing操作你可以就数出菜单中元素的个数，并得
     * 到菜肴热量总和、平均值、最大值和最小值
     */
    @Test
    public void streamSummarizing(){
        IntSummaryStatistics menuStatistics =
                menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics.getAverage());
    }
    /**
     * 同样，相应的summarizingLong和summarizingDouble工厂方法有相关的LongSummary-
     * Statistics和DoubleSummaryStatistics类型,
     * 适用于收集的属性是原始类型long或double的情况。
     */

    @Test
    public void streamSummarizing2(){
        LongSummaryStatistics menuStatistics =
                menu.stream().collect(summarizingLong(Dish::getCalories));
        System.out.println(menuStatistics.getAverage());
    }
    /**
     * 连接字符串
     */

    @Test
    public void streamJoin(){
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu);
    }

}

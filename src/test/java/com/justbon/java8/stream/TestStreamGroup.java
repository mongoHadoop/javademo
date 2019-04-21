package com.justbon.java8.stream;

import com.alibaba.fastjson.JSONObject;
import com.justbon.java8.stream.pojo.Dish;
import org.junit.Test;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

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

    /**
     *
     * 我们来看看这个功能的第二个例子：假设你要把菜单中的菜按照类型进行分类，
     * 有肉的放一组，有鱼的放一组，其他的都放另一组。用Collectors.groupingBy工厂方法返回
     * 的收集器就可以轻松地完成这项任务，如下所示：
     * Map<Dish.Type, List<Dish>> dishesByType =
     * menu.stream().collect(groupingBy(Dish::getType));
     *
     * 还要注意，普通的单参数groupingBy(f)（其中f是分类函数）实际上是groupingBy(f,
     * toList())的简便写法。
     */

    @Test
    public void streamGroup(){

        Map<Dish.Type, List<Dish>> dishesByType =
        menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(JSONObject.toJSONString(dishesByType));

        /**
         *
         * 分类函数不一定像方法引用那样可用，因为你想用以分类的条件可能比简单的属性访
         * 问器要复杂。例如，你可能想把热量不到400卡路里的菜划分为“低热量”（diet），热量400到700
         * 卡路里的菜划为“普通”（normal），高于700卡路里的划为“高热量”（fat）
         * 即自定义 分组
         */
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return
                            CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                } ));

        System.out.println("dish category:"+JSONObject.toJSONString(dishesByCaloricLevel));

    }

    public enum CaloricLevel { DIET, NORMAL, FAT }

    /**
     *
     * 多级分组
     *
     * 要实现多级分组，我们可以使用一个由双参数版本的Collectors.groupingBy工厂方法创
     * 建的收集器，它除了普通的分类函数之外，还可以接受collector类型的第二个参数。那么要进
     * 行二级分组的话，我们可以把一个内层groupingBy传递给外层groupingBy，并定义一个为流
     * 中项目分类的二级标准
     *
     */
    @Test
    public void testStreamLevelGroup(){

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;else return CaloricLevel.FAT;
                                } )
                        )
                );

        System.out.println(JSONObject.toJSONString(dishesByTypeCaloricLevel));
    }

    /**
     * 按子组收集数据
     *
     * 我们看到可以把第二个groupingBy收集器传递给外层收集器来实现多级分
     * 组。但进一步说，传递给第一个groupingBy的第二个收集器可以是任何类型
     * 而不一定是个groupingBy。例如，要数一数菜单中每类菜有多少个，可以传递counting收集器作为
     * groupingBy收集器的第二个参数：
     */
    @Test
    public void testStreamGroupCount(){
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));
        System.out.println(JSONObject.toJSONString(typesCount));

    }

    /**
     *  再举一个例子，你可以把前面用于查找菜单中热量最高的菜肴的收集器改一改，按照菜的类
     *         型分类：
     */

    @Test
    public void testStreamGroupMax(){

        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))));

        System.out.println(JSONObject.toJSONString(mostCaloricByType));
    }
    /**
     * 把收集器的结果转换为另一种类型
     *
     * 这个工厂方法接受两个参数——要转换的收集器以及转换函数，并返回另一个收集器。这个
     * 收集器相当于旧收集器的一个包装，collect操作的最后一步就是将返回值用转换函数做一个映
     * 射。在这里，被包起来的收集器就是用maxBy建立的那个，而转换函数Optional::get则把返
     * 回的Optional中的值提取出来。前面已经说过，这个操作放在这里是安全的，因为reducing
     * 收集器永远都不会返回Optional.empty()。
     */
    @Test
    public void  testStreamGroupMaxConvert(){
        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)));
        System.out.println(JSONObject.toJSONString(mostCaloricByType));
    }

    /**
     * 把好几个收集器嵌套起来很常见，它们之间到底发生了什么可能不那么明显。图6-6可以直
     * 观地展示它们是怎么工作的。从最外层开始逐层向里，注意以
     *
     * 一般来说，通过groupingBy工厂方法的第二个参数传递的收集器将会对分到同一组中的所
     * 有流元素执行进一步归约操作。例如，你还重用求出所有菜肴热量总和的收集器，不过这次是对
     * 每一组Dish求和：
     */

    @Test
    public  void testStreamGroupSummingInt(){
        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));
        System.out.println(JSONObject.toJSONString(totalCaloriesByType));

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                else return CaloricLevel.FAT; },
                                toSet() )));
    }
    /**
     * 然而常常和groupingBy联合使用的另一个收集器是mapping方法生成的。这个方法接受两
     * 个参数：一个函数对流中的元素做变换，另一个则将变换的结果对象收集起来。其目的是在累加
     * 之前对每个输入元素应用一个映射函数，这样就可以让接受特定类型元素的收集器适应不同类型
     * 的对象。我们来看一个使用这个收集器的实际例子。比方说你想要知道，对于每种类型的Dish，
     * 菜单中都有哪些CaloricLevel。我们可以把groupingBy和mapping收集器结合起来，如下所示
     */
    @Test
    public  void testStreamGroupMapping(){
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                else return CaloricLevel.FAT; },
                                toSet() )));

        System.out.println(caloricLevelsByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                else return CaloricLevel.FAT; },
                                toCollection(HashSet::new) )));
        System.out.println(caloricLevelsByType2);

    }

}

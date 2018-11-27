package com.justbon.java8.methodrefrance;

import com.justbon.java8.methodrefrance.pojo.Apple;
import com.justbon.java8.methodrefrance.pojo.Apple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestMethod2 {

    //构造函数引用
    public static void main(String[] args) {
      /*  Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();*/
        //等价与
        Supplier<Apple> c2 = () -> new Apple(1);

        //也就是说 方法引用的 其实也lamdba一样,
        // 只要方法签名一致就可以用函数接口

        Function<Integer, Apple> c3 = Apple::new; Apple a2 = c3.apply(110);
        //等价于下面
        Function<Integer, Apple> c4 = (weight) -> new Apple(weight);
        Apple a4 = c4.apply(110);


        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);

        //如果你有一个具有两个参数的构造函数Apple(String color, Integer weight)，那么 它就适合BiFunction接口的签名，于是你可以这样写：
        BiFunction<String, Integer, Apple2> c5 = Apple2::new;
        Apple2 c6 = c5.apply("green", 110);
        //等价于 lamda
        BiFunction<String, Integer, Apple2> c5bak = (color, weight) -> new Apple2(color, weight);
        Apple2 c6bak = c5bak.apply("green", 110);
        ///以上函数接口 均是agr....是方法签名 args 最后一个参数代表返回结果

    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();

        for(Integer e: list){
            result.add(f.apply(e));
        }
        return result;
    }

}

package com.justbon.java8.lamda;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.justbon.java8.methodrefrance.pojo.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class TestLamda {
    List<Integer> weights = Lists.newArrayList(7, 3, 4, 10);
    @Test
    /**
     * Lambda表达式的签名与Comparator的函数描述符兼容。利用前面所述的方法，这个例子可
     * 以用方法引用改写成下面的样子
     *
     */
    public void test(){
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        /**
         *
         *  Comparator描述了
         * 一个具有(T, T) -> int签名的函数描述符。你可以利用String类中的compareToIgnoreCase
         * 方法来定义一个Lambda表达式（注意compareToIgnoreCase是String类中预先定义的）。
         *
         * 请注意，编译器会进行一种与Lambda表达式类似的类型检查过程，来确定对于给定的函数
         * 式接口这个方法引用是否有效：方法引用的签名必须和上下文类型匹配
         */
        str.sort(String::compareToIgnoreCase);

        str.forEach(e-> System.out.println(e));
    }

    @Test
    public void testMethod(){
        //下列Lambda表达式的等效方法引用是什么？
         Function<String, Integer> stringToInteger =
                (String s) -> Integer.parseInt(s);

        Function<String, Integer> stringToInteger1 = Integer::parseInt;

         BiPredicate<List<String>, String> contains =
                (list, element) -> list.contains(element);

        BiPredicate<List<String>, String> contains2 = List::contains;
      /**
       (1) 这个Lambda表达式将其参数传给了Integer的静态方法parseInt。这种方法接受一个需要解析的String
        ,并返回一个Integer,（Lambda表达式调用静态方法）来重写Lambda表达式，如下所示：
        Function<String, Integer> stringToInteger = Integer::parseInt;
       (2) 这个Lambda使用其第一个参数，调用其contains方法。由于第一个参数是List类型
        的，你可以使用图3-5中的办法➋，如下所示：
        BiPredicate<List<String>, String> contains = List::contains;
        是因为，目标类型描述的函数描述符是 (List<String>,String) -> boolean，而
        List::contains可以被解包成这个函数描述符*/



    }

    /**
     *
     * 构造方法
     * 构造函数引用
     * 可以利用它的名称和关键字new来创建它的一个引用：
     * ClassName::new。它的功能与指向静态方法的引用类似。例如，假设有一个构造函数没有参数。
     * 它适合Supplier的签名() -> Apple。你可以这样做
     */

    @Test
    public void testNewContruct(){
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        System.out.println(a1);
        /**
         *
         * 如果你的构造函数的签名是Apple(Integer weight)，那么它就适合Function接口的签名,
         * 于是你可以这样写：
         *
         */

        Function<Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(110);
        System.out.println(a2);

        //这就等价于：
        Function<Integer, Apple> c2_1 = (weight) -> new Apple(weight);
        Apple a2_1 = c2_1.apply(110);
        System.out.println(a2_1);

        /**
         * 在下面的代码中，一个由Integer构成的List中的每个元素都通过我们前面定义的类似的
         * map方法传递给了Apple的构造函数，得到了一个具有不同重量苹果的List：
         * List<Integer> weights = Arrays.asList(7, 3, 4, 10);
         * List<Apple> apples = map(weights, Apple::new);
         */

        List<Apple> apples = weights.stream().map(weight->{
            Function<Integer, Apple> f=Apple::new;
            return  f.apply(weight);
        }).collect(toList());
        apples.forEach(e-> System.out.println(JSONObject.toJSONString(e)));
    }
    /**
     * 如果你有一个具有两个参数的构造函数Apple(String color, Integer weight)，那么
     * 它就适合BiFunction接口的签名，于是你可以这样写
     *
     *
     * 你已经看到了如何将有零个、一个、两个参数的构造函数转变为构造函数引用。那要怎么
     * 样才能对具有三个参数的构造函数，比如Color(int, int, int)，使用构造函数引用呢？
     * 答案：你看，构造函数引用的语法是ClassName::new，那么在这个例子里面就是
     * Color::new。但是你需要与构造函数引用的签名匹配的函数式接口。但是语言本身并没有提
     * 供这样的函数式接口，你可以自己创建一个：
     * public interface TriFunction<T, U, V, R>{
     *   R apply(T t, U u, V v);
     * }
     * 现在你可以像下面这样使用构造函数引用了：
     * TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
     *
     */

    @Test
    public void testNewContruct2(){
        BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple c4 = c3.apply("green", 110);

        //这就等价于：
        BiFunction<String, Integer, Apple> c3_1 =
                (color, weight) -> new Apple(color, weight);
        Apple c4_1= c3_1.apply("green", 110);


        List<Apple> apples = weights.stream().map(weight->{
            BiFunction<String, Integer, Apple> ap3=Apple::new;
            Apple ap4 = ap3.apply("green", weight);
            return  ap4;
        }).collect(toList());
        apples.forEach(e-> System.out.println(JSONObject.toJSONString(e)));
    }

}

package com.justbon.java8.lamda;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.justbon.java8.bean.Letter;
import com.justbon.java8.methodrefrance.pojo.Apple;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 *
 *
 *方法引用的简化模式:
 (Apple a) -> a.getWeight() Apple::getWeight
 () -> Thread.currentThread().dumpStack() Thread.currentThread()::dumpStack
 (str, i) -> str.substring(i) String::substring
 (String s) -> System.out.println(s) System.out::println
 *
 */
public class TestLamda {
    List<Integer> weights = Lists.newArrayList(7, 3, 4, 10);
    @Test
    /**
     * Lambda表达式的签名与Comparator的函数描述符兼容。利用前面所述的方法，这个例子可
     * 以用方法引用改写成下面的样子
     *
     * 你的代码还能变得更易读一点吗？Comparator具有一个叫作comparing的静态辅助方法，
     * 它可以接受一个Function来提取Comparable键值，并生成一个Comparator对象
     *
     */
    public void testComparator(){
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
    public void testFunction(){
        //下列Lambda表达式的等效方法引用是什么？
         Function<String, Integer> stringToInteger =
                (String s) -> Integer.parseInt(s);

        Function<String, Integer> stringToInteger1 = Integer::parseInt;
        //apply 函数的意思是说 把T  当做输入,输出 R结果
        System.out.println(stringToInteger1.apply("100"));
      /**
       (1) 这个Lambda表达式将其参数传给了Integer的静态方法parseInt。这种方法接受一个需要解析的String
        ,并返回一个Integer,（Lambda表达式调用静态方法）来重写Lambda表达式，如下所示：
        Function<String, Integer> stringToInteger = Integer::parseInt;
      **/

    }

    /**
     *  (2) 这个Lambda使用其第一个参数，调用其contains方法。由于第一个参数是List类型
     *         的，你可以使用图3-5中的办法➋，如下所示：
     *         BiPredicate<List<String>, String> contains = List::contains;
     *         是因为，目标类型描述的函数描述符是 (List<String>,String) -> boolean，而
     *         List::contains可以被解包成这个函数描述符
     **/
    @Test
    public void testBiPredicate(){
        BiPredicate<List<Integer>, Integer> contains =
                (list, element) -> list.contains(element);
        BiPredicate<List<Integer>, Integer> contains2 = List::contains;
        // boolean test(T t, U u); 即 把T 的表达式应用于 U

        System.out.println(contains2.test(weights,3));
        /**
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
        //先来创建一个供给型接口对象：
        Supplier<String> supplier = () -> new String();
        //在这个lammbda表达式中只做了一件事，就是返回一个新的Test对象，而这种形式可以更简化：

        Supplier<String> supplier2 = String::new;
        System.out.println(supplier2.get());
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
        apples.stream().map(JSONObject::toJSONString).forEach(
                System.out::println);
    }

    /**
     * 综合例子
     */

    @Test
    public void testComparatorAppleDemo(){

        List<Apple> apples = weights.stream().sorted(Integer::compare).map(weight->{
            BiFunction<String, Integer, Apple> ap3=Apple::new;
            Apple ap4 = ap3.apply("green", weight);
            return  ap4;
        }).collect(toList());

        apples.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        apples.forEach(e-> System.out.println(e));

        /**
         * 你的代码还能变得更易读一点吗？Comparator具有一个叫作comparing的静态辅助方法，
         * 它可以接受一个Function来提取Comparable键值，并生成一个Comparator对象
         * 注意其返回了一个
         * return (Comparator<T> & Serializable)
         *             (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
         *
         */
        Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());

        /**
         *
         * import static java.util.Comparator.comparing;
         * 在把代码改紧酬点
         * 注意其返回了一个
         * return (Comparator<T> & Serializable)
         *   (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
         */
        apples.sort(comparing((a) -> a.getWeight()));

        /**
         *
         *
         3.7.4 第 4 步：使用方法引用
         前面解释过，方法引用就是替代那些转发参数的Lambda表达式的语法糖。你可以用方法引
         用让你的代码更简洁（假设你静态导入了java.util.Comparator.comparing）：
         inventory.sort(comparing(Apple::getWeight));
         恭喜你，这就是你的最终解决方案！这比Java 8之前的代码好在哪儿呢？它比较短；它的意
         思也很明显，并且代码读起来和问题描述差不多：“对库存进行排序，比较苹果的重量。

         comparing(Apple::getWeight)
         (arg0,rest)->arg0.instantMethod(rest);
                     |
                     | arg0 是 ClassName类型的
         ClassName::instantMethod
         因此
         List<String> str = Arrays.asList("a","b","A","B");
         str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

         可以使用
         str.sort(String::compareToIgnoreCase); String::compareToIgnoreCase====(s1, s2) -> s1.compareToIgnoreCase(s2)
         而这里
         Apple::getWeight 没有arg0,rest相关,因此需要comparing辅助函数构 comparing 造了一个比较相关函数

         return (Comparator<T> & Serializable)
           (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));


         例如：

         (Apple a) -> a.getWeight() Apple::getWeight
         () -> Thread.currentThread().dumpStack() Thread.currentThread()::dumpStack
         (str, i) -> str.substring(i) String::substring
         (String s) -> System.out.println(s) System.out::println

         */

        apples.sort(comparing(Apple::getWeight));
    }

    /**
     *   比较器复合
     *         1. 逆序
     *         如果你想要对苹果按重量递减排序怎么办？
     *
     *
     * 2. 比较器链
     * 上面说得都很好，但如果发现有两个苹果一样重怎么办？哪个苹果应该排在前面呢？你可能
     * 需要再提供一个Comparator来进一步定义这个比较。比如，在按重量比较两个苹果之后，你可
     * 能想要按原产国排序。thenComparing方法就是做这个用的。它接受一个函数作为参数（就像
     * comparing方法一样），如果两个对象用第一个Comparator比较之后是一样的，就提供第二个
     * Comparator。你又可以优雅地解决这个问题了：
     * inventory.sort(comparing(Apple::getWeight)
     *   .reversed()
     *   .thenComparing(Apple::getColor));
     */
    @Test
    public void testComparatorComplex(){
        List<Apple> apples = weights.stream().map(weight->{
            BiFunction<String, Integer, Apple> ap3=Apple::new;
            Apple ap4 = ap3.apply("green", weight);
            return  ap4;
        }).collect(toList());
        apples.sort(comparing(Apple::getWeight).reversed());
        apples.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
    }

    /**
     *
     * 3.8.2 谓词复合 Predicate就是 谓词
     * 谓词接口包括三个方法：negate、and和or，让你可以重用已有的Predicate来创建更复
     * 杂的谓词。比如，你可以使用negate方法来返回一个Predicate的非，比如苹果不是红的：
     * Predicate<Apple> notRedApple = redApple.negate();
     * 你可能想要把两个Lambda用and方法组合起来，比如一个苹果既是红色又比较重：
     * Predicate<Apple> redAndHeavyApple =
     *  redApple.and(a -> a.getWeight() > 150);
     * 你可以进一步组合谓词，表达要么是重（150克以上）的红苹果，要么是绿苹果：
     * Predicate<Apple> redAndHeavyAppleOrGreen =
     *  redApple.and(a -> a.getWeight() > 150)
     *  .or(a -> "green".equals(a.getColor()));
     * 这一点为什么很好呢？从简单Lambda表达式出发，你可以构建更复杂的表达式，但读起来
     * 仍然和问题的陈述差不多！请注意，and和or方法是按照在表达式链中的位置，从左向右确定优
     * 先级的。因此，a.or(b).and(c)可以看作(a || b) && c
     */

    @Test
    public void testComplexPredicate2(){
        Map<Integer, String> map = Maps.newHashMap();
        map.put(2,"white");
        map.put(3,"green");
        map.put(4,"redhalf");
        map.put(7,"red");
        map.put(10,"red");

        List<Apple> apples = weights.stream().map(weight->{
            BiFunction<String, Integer, Apple> ap3=Apple::new;
            Apple ap4 = ap3.apply(map.get(weight), weight);
            return  ap4;
        }).collect(toList());

        Predicate<Apple>  redApple= (a->a.getColor().equals("red"));
        Predicate<Apple>  notredApple=redApple.negate();//对现有Predicate求反

        Predicate<Apple>  redAndHeavyApple=redApple.and(a->a.getWeight()>3);//大于三斤以上的

        Predicate<Apple>  redAndHeavyOrGreenApple=redApple.and(a->a.getWeight()>3).or(a->a.getColor().equals("green"));//大于三斤或是green的苹果

        List<Apple> redApples  =apples.stream().filter(redApple).collect(toList());
        System.out.println("red apple:"+JSONObject.toJSONString(redApples));

        List<Apple> notredApples  =apples.stream().filter(notredApple).collect(toList());
        System.out.println("not red apples:"+JSONObject.toJSONString(notredApples));

        List<Apple> redAndHeavyApples  =apples.stream().filter(redAndHeavyApple).collect(toList());
        System.out.println("red and havety >3 apples :"+redAndHeavyApples);

        List<Apple> redAndHeavyOrGreenApples  =apples.stream().filter(redAndHeavyOrGreenApple).collect(toList());
        System.out.println("red and green apples:"+JSONObject.toJSONString(redAndHeavyOrGreenApples));

    }

    /**
     * .8.3 函数复合
     * 最后，你还可以把Function接口所代表的Lambda表达式复合起来。
     * Function接口为此配了andThen和compose两个默认方法，它们都会返回Function的一个实例。
     * 1.andThen方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数。
     * 比如，假设有一个函数f给数字加1 (x -> x + 1)，另一个函数g给数字乘2，你可以将它们组
     * 合成一个函数h，先给数字加1，再给结果乘2：
     * Function<Integer, Integer> f = x -> x + 1;
     * Function<Integer, Integer> g = x -> x * 2;
     * Function<Integer, Integer> h = f.andThen(g);
     * int result = h.apply(1);
     * 2 你也可以类似地使用compose方法，先把给定的函数用作compose的参数里面给的那个函
     * 数，然后再把函数本身用于结果。比如在上一个例子里用compose的话，它将意味着f(g(x))，
     * 而andThen则意味着g(f(x))：
     * Function<Integer, Integer> f = x -> x + 1;
     * Function<Integer, Integer> g = x -> x * 2;
     * Function<Integer, Integer> h = f.compose(g);
     * int result = h.apply(1);
     * 图3-6说明了andThen和compose之间的区别。
     * 数学上会写作g(f(x))或
     * (g o f)(x)
     * 这将返回4
     * 数学上会写作f(g(x))
     * 或(f o g)(x)
     * 这将返
     *
     */


    @Test
    public void testFunctionComplex(){

        /**
         * 比如，假设有一个函数f给数字加1 (x -> x + 1)，另一个函数g给数字乘2，你可以将它们组
         * 另一个函数g给数字乘2，你可以将它们组 合成一个函数h，先给数字加1，再给结果乘2：
         *即数学里面的 复合函数 y=g(f(x))
         */
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println(result);
        /**
         * 用compose的话，它将意味着f(g(x))
         */
        Function<Integer, Integer> h2=f.compose(g);
        int result2 = h2.apply(1);
        System.out.println(result2);
    }

    /**
     * function流水线处理
     */
    @Test
    public void testFunctionFlow(){
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println(transformationPipeline);
        String text="Hello";
        System.out.println(procces(text,transformationPipeline));
        String text2="ganli labda";
        //apply 函数的意思是说 把T  当做输入,输出 R结果
        System.out.println(transformationPipeline.apply(text2));
    }
    private String procces(String text,Function<String,String> function){
        return function.apply(text);
    }

}

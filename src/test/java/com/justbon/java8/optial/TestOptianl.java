package com.justbon.java8.optial;
import com.justbon.App;
import com.justbon.java8.methodrefrance.pojo.Apple;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestOptianl {

    /**
     *
     * 把指定的值封装为Optional对象，如果指定的值为null，则抛出NullPointerException
     */
    @Test
    public void testOf(){

        //创建一个值为张三的String类型的Optional
        Optional<String> ofOptional = Optional.of("张三");
        System.out.println(ofOptional.get());
        //如果我们用of方法创建Optional对象时，所传入的值为null，则抛出NullPointerException如下图所示
        Optional<String> nullOptional = Optional.of(null);

    }

    /**
     *
     * 把指定的值封装为Optional对象，如果指定的值为null，则创建一个空的Optional对象
     */
    @Test
    public  void testofNullable(){
        //为指定的值创建Optional对象，不管所传入的值为null不为null，创建的时候都不会报错
        Optional<String> nullOptional = Optional.ofNullable(null);
        nullOptional.ifPresent((o)-> System.out.println(o.toString()));
        System.out.println(nullOptional.orElse("李四"));
        Optional<String> nullOptional2 = Optional.ofNullable("lisi");
        nullOptional2.ifPresent((o)-> System.out.println(o.toString()));
        System.out.println(nullOptional2.orElse("李四"));
        //创建一个空的String类型的Optional对象
        Optional<String> emptyOptional = Optional.empty();
    }

    /**
     * 如果我们创建的Optional对象中有值存在则返回此值，如果没有值存在，则get()会抛出
     * NoSuchElementException异常。小demo如下：
     */
    @Test
    public void testGet(){
        Optional<String> stringOptional = Optional.of("张三");
        System.out.println(stringOptional.get());
        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.get());
    }

    /**
     * 如果创建的Optional.orElse 中有值存在，则返回此值，否则返回一个默认值
     */
    @Test
    public void testorElse(){
        Optional<String> stringOptional = Optional.of("张三");
        System.out.println(stringOptional.orElse("zhangsan"));
        Optional<String> emptyOptional = Optional.ofNullable(null);
        System.out.println(emptyOptional.orElse("李四"));
    }

    /**
     * 如果创建的Optional中有值存在，则返回此值，否则返回一个由Supplier接口生成的值
     */
    @Test
    public void testorElseGet(){
        Optional<String> stringOptional = Optional.of("张三");
        System.out.println(stringOptional.orElseGet(() -> "zhangsan"));

        Optional<String> emptyOptional = Optional.ofNullable(null);
        System.out.println(emptyOptional.orElseGet(() -> "orElseGet"));
    }

    /**
     *ifPresent方法的参数是一个Consumer的实现类，Consumer类包含一个抽象方法，该抽象方法对传入的值进行处理，只处理没有返回值。
     */
    @Test
    public void testifPresent(){

        //ifPresent方法的参数是一个Consumer的实现类，Consumer类包含一个抽象方法，该抽象方法对传入的值进行处理，只处理没有返回值。
        Optional<String> stringOptional = Optional.of("zhangsan");
        stringOptional.ifPresent(System.out::println);

    }

    /**
     * 使用 map 从 Optional 对象中提取和转换值
     *
     * 从概念上，这与我们在第4章和第5章中看到的stream流的map方法相差无几。map操作会将提供的
     * 函数应用于流的每个元素。你可以把Optional对象看成一种特殊的集合数据，它至多包含一个
     * 元素。如果Optional包含一个值，那函数就将该值作为参数传递给map，对该值进行转换
     */
    @Test
    public void testMap(){
        Supplier<Apple> apple = Apple::new;
        Apple a= apple.get();
        a.setColor("green");

        Optional<Apple> appleOptional = Optional.of(a);
        Optional<String> color=appleOptional.map(Apple::getColor);
        color.ifPresent(System.out::println);
    }
}

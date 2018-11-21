package com.justbon.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型T
 * 的对象，没有返回（void）。你如果需要访问类型T的对象，并对其执行某些操作，就可以使用
 * 这个接口。
 * java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型T
 * 的对象，没有返回（void）。你如果需要访问类型T的对象，并对其执行某些操作，就可以使用
 * 这个接口。
 * @FunctionalInterface
 * public interface Consumer<T>{
 *  void accept(T t);
 * }
 *
 */
public class TestConsumer {

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T i: list){
            c.accept(i);
        }
    }

    public static void main(String[] args) {
        forEach(
                Arrays.asList(1,2,3,4,5),
                (Integer i) -> System.out.println(i)
        );
    }

}

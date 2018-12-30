package com.justbon.java8.stream.flatmap;

import com.justbon.java8.stream.pojo.Dish;

import java.util.Arrays;
import java.util.List;

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



        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List <String[]>wordLengths
       = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

/**
 * 这个方法的问题在于，传递给map方法的Lambda为每个单词返回了一个String[]（String
 *         列表）。因此， map 返回的流实际上是Stream<String[]> 类型的。你真正想要的是用
 *         Stream<String>来表示一个字符流
 */


        System.out.println(wordLengths);

    }
}

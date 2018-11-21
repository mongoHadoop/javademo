package com.justbon.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 请注意，任何函数式接口都不允许抛出受检异常（checked exception）。如果你需要Lambda
 * 表达式来抛出异常，有两种办法：定义一个自己的函数式接口，并声明受检异常，或者把Lambda
 * 包在一个try/catch块中。
 *
 * 比如Function<T, R>，没有办法自己
 * 创建一个（你会在下一章看到，Stream API中大量使用表3-2中的函数式接口）。这种情况下，
 * 你可以显式捕捉受检异常：
 * Function<BufferedReader, String> f = (BufferedReader b) -> {
 *  try {
 *      return b.readLine();
 *  }
 *  catch(IOException e){
 *      e
 *  }
 */
public class TestFunction {

    public static <T, R> List<R> map(List<T> list,
                                     Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }

    public static void main(String[] args) {
        /**
         * 即 (String s) -> s.length() 实现了Function接口 R apply(T t);
         * 然后应用到 result.add(f.apply(s));
         *
         */
        List<Integer> l = map(
                Arrays.asList("lambdas","in","action"),
                (String s) -> s.length()
        );
    }
}

package com.justbon.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @FunctionalInterface
 * public interface Predicate<T>{
 *  boolean test(T t);
 * }
 * java.util.function.Predicate<T>接口定义了一个名叫test的抽象方法，它接受泛型
 * T对象，并返回一个boolean。这恰恰和你先前创建的一样，现在就可以直接使用了
 *
 */

public class TestPredicate {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T s: list){
            if(p.test(s)){
                results.add(s);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List listOfStrings =new ArrayList();

        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
    }
}

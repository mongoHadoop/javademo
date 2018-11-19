package com.justbon.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @FunctionalInterface
 * public interface Predicate<T>{
 *  boolean test(T t);
 * }
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

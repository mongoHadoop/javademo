package com.justbon.java8.lambda.methodrefrence;

import java.util.function.Function;
import java.util.function.Supplier;

public class TestMethod3 {

    public static void main(String[] args) {


        //先来创建一个供给型接口对象：

        Supplier<String> supplier = () -> new String();
        //在这个lammbda表达式中只做了一件事，就是返回一个新的Test对象，而这种形式可以更简化：

        Supplier<String> supplier2 = String::new;

        /**
         *  提炼一下构造引用的语法：
         *
         *         类名::new
         *
         *         当通过含参构造方法创建对象，并且参数列表与抽象方法的参数列表一致，也就是下面的这种形式：
         */

    }
}

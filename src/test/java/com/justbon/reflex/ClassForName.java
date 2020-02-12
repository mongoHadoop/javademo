package com.justbon.reflex;

public class ClassForName {

    static {
        System.out.println("执行静态代码快");
    }


    public static String staticMethod(){
        System.out.println("执行了静态方法");
        String s ="给静态字段赋值";
        return s;
    }
    private static String staticField = staticMethod();
}

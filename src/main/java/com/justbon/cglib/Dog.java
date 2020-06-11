package com.justbon.cglib;

/**
 * @author ganli
 * @version 1.0
 * @file Dog.java
 * @Modified By：
 * @date 2020-06-11 上午10:23
 * @description
 */
public class Dog{

    final public void run(String name) {
        System.out.println("狗"+name+"----run");
    }

    public void eat() {
        System.out.println("狗----eat");
    }
}

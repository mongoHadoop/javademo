package com.justbon.spi;

/**
 * @author ganli
 * @version 1.0
 * @file PythonDeveloper.java
 * @Modified By：
 * @date 2020-07-03 上午11:33
 * @description
 */
public class PythonDeveloper implements Developer {

    @Override
    public void sayHi() {
        System.out.println("Hi, I am a Python Developer.");
    }
}
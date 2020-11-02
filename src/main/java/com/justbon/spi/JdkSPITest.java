package com.justbon.spi;

import java.util.ServiceLoader;

/**
 * @author ganli
 * @version 1.0
 * @file JdkSPITest.java
 * @Modified By：
 * @date 2020-07-03 上午11:35
 * @description
 */
public class JdkSPITest {
    public static void main(String[] args) {
        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);
        serviceLoader.forEach(Developer::sayHi);
    }
}

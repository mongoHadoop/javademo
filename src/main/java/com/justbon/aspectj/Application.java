package com.justbon.aspectj;

/**
 * @author ganli
 * @version 1.0
 * @file Application.java
 * @Modified By：
 * @date 2020-01-14 下午4:47
 * @description
 */
public class Application {

    public static void main(String[] args) {
        testCompileTime();
    }
    public static void testCompileTime() {
        Account account = new Account();
        System.out.println("==================");
        account.pay(10);
        account.pay(50);
        System.out.println("==================");
    }
}
package com.justbon.aspectj;

/**
 * @author ganli
 * @version 1.0
 * @file Account.java
 * @Modified By：
 * @date 2020-01-14 下午4:39
 * @description
 */
public class Account {

    int balance = 20;

    public boolean pay(int amount) {
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }
}

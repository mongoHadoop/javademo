package com.justbon.thread.lock.conditon;

import com.justbon.thread.lock.ReenterLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ganli
 * @version 1.0
 * @file Main.java
 * @Modified By：
 * @date 2019-12-11 下午2:11
 * @description
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ReenterLockConditonDemo reenterLockDemo = new ReenterLockConditonDemo(lock,condition);
        Thread thread =new Thread(reenterLockDemo);
        thread.start();
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName());
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}

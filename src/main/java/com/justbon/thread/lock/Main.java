package com.justbon.thread.lock;

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
        ReenterLockDemo reenterLockDemo = new ReenterLockDemo(lock);
        Thread thread =new Thread(reenterLockDemo);
        thread.start();
        Thread thread1 =new Thread(reenterLockDemo);
        thread1.start();
        thread1.join();
        thread.join();
    }
}

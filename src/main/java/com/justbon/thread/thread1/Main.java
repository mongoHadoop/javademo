package com.justbon.thread.thread1;

/**
 * Created by mongo on 2018/8/11.
 * 示例如何 开启一个线程
 */
public class Main {

    public static void main(String[] args) {
        MyRunnable task = new MyRunnable();
        Thread t = new Thread(task);
        t.start();
        System.out.println("This is main ");
    }
}

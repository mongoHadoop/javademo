package com.justbon.thread.thread1.stopthread;


/**
 * Created by mongo on 2018/8/11.
 * 示例如何 停止一个线程
 */
public class Main {

    public static void main(String[] args) {
        MyRunnable2 task = new MyRunnable2();
        Thread t = new Thread(task);
        t.start();
        try {
            Thread.sleep(10);
            t.interrupt();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("This is main ");
    }
}

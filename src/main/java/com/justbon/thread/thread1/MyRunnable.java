package com.justbon.thread.thread1;

/**
 * Created by mongo on 2018/8/11.
 */
public class MyRunnable implements Runnable{

    public void run(){
        //设置线程任务片 名称
        Thread.currentThread().setName("MyRunnable 1");
        System.out.println(  Thread.currentThread().getName() +"is run");
    }

}

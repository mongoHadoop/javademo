package com.justbon.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author ganli
 * @version 1.0
 * @file CountDownLatchDemo.java
 * @Modified By：
 * @date 2019-12-11 下午2:28
 * @description
 */
public class CountDownLatchDemo implements Runnable{

    private CountDownLatch end;

    public CountDownLatchDemo(CountDownLatch end){
        this.end=end;
    }

    @Override
    public void run() {

        try{
            Thread.sleep(1000);
            System.out.println("name:"+Thread.currentThread().getName());

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            end.countDown();
        }

    }


}

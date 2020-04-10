package com.justbon.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ganli
 * @version 1.0
 * @file AtomicIntegrationDemo.java
 * @Modified By：
 * @date 2020-04-01 下午5:32
 * @description
 */
public class AtomicIntegrationDemo {

    static AtomicInteger i = new AtomicInteger();
     static  class AddThread implements Runnable{


        @Override
        public void run() {
            for (int k=0;k<1000;k++){
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Thread[] ts = new Thread[ 10];

        for (int k=0;k<10;k++){
            ts[k] =new Thread(new AddThread());
        }
        for (int k=0;k<10;k++){ts[k].start();}
        for (int k=0;k<10;k++){ts[k].join();}
        System.out.println(i.get());
    }
}

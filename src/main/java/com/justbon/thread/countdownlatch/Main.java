package com.justbon.thread.countdownlatch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author ganli
 * @version 1.0
 * @file Main.java
 * @Modified By：
 * @date 2019-12-11 下午2:31
 * @description
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        int count =100;
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("bmm-sync-table-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1020), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        CountDownLatch latch = new CountDownLatch(count);
        for (int i=0;i<count;i++) {
            CountDownLatchDemo demo = new CountDownLatchDemo(latch);
            threadPoolExecutor.execute(demo);
        }
        latch.await();
        threadPoolExecutor.shutdown();
        System.out.println("fished");
    }
}

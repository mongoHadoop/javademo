package com.justbon.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ganli
 * @version 1.0
 * @file ReenterLockDemo.java
 * @Modified By：
 * @date 2019-12-11 下午2:10
 * @description
 */
public class ReenterLockDemo implements Runnable{

    public ReentrantLock lock ;
    public ReenterLockDemo(ReentrantLock lock){
        this.lock=lock;
    }
    public int sum=0;
    @Override
    public void run() {
        for (int j=0;j<100;j++){
            lock.lock();
            try {
                sum++;
                System.out.println(sum);
            }finally {
                lock.unlock();
            }
        }
    }
}

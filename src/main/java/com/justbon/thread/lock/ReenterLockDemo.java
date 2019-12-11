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

    @Override
    public void run() {
        for (int j=0;j<100000;j++){
            lock.lock();
            try {
                j++;
                System.out.println(j);
            }finally {
                lock.unlock();
            }
        }
    }
}

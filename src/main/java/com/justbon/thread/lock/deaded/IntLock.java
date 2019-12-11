package com.justbon.thread.lock.deaded;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ganli
 * @version 1.0
 * @file IntLock.java
 * @Modified By：
 * @date 2019-12-11 下午2:56
 * @description
 */
public class IntLock implements Runnable{

    private ReentrantLock lock1;
    private ReentrantLock lock2;
    private int lock;

    public IntLock(ReentrantLock lock1,ReentrantLock lock2,int lock){
        this.lock1=lock1;
        this.lock2=lock2;
        this.lock = lock;
    }

    @Override
    public void run() {
        if(lock==1){
            try {
                lock1.lockInterruptibly();
                Thread.sleep(1000);
                lock2.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else {
            try {
                lock2.lockInterruptibly();
                Thread.sleep(1000);
                lock1.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

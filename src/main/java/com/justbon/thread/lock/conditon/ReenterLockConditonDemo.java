package com.justbon.thread.lock.conditon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ganli
 * @version 1.0
 * @file ReenterLockDemo.java
 * @Modified By：
 * @date 2019-12-11 下午2:10
 * @description
 */
public class ReenterLockConditonDemo implements Runnable{

    private ReentrantLock lock ;

    private Condition condition;

    public ReenterLockConditonDemo(ReentrantLock lock,Condition condition){
        this.lock=lock;
        this.condition=condition;
    }

    @Override
    public void run() {

            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                condition.await();
                System.out.println("keep going on");

            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
}


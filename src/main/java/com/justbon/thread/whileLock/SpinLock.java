package com.justbon.thread.whileLock;

/**
 * @author ganli
 * @version 1.0
 * @file SpinLock.java
 * @Modified By：
 * @date 2020-04-10 下午2:04
 * @description Java简单实现一个自旋锁
 */
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock
{
    //Java中的原子操作（CAS）
    //持有自旋锁的线程对象
    AtomicReference<Thread> owner=new AtomicReference<Thread>();
    private int count;

    public void lock()
    {
        Thread curThread=Thread.currentThread();
        //lock函数将owner设置为当前线程，并且预测原来的值为空
        //当有第二个线程调用lock操作时由于owner的值不为空，导致循环
        //一直被执行，直至第一个线程调用unclock函数将owner设置为null，第二个线程才能进入临界区
        while(!owner.compareAndSet(null, curThread))
        {

        }
    }

    //unlock将owner的值设置为null，并且预测值为当前线程
    public void unlock()
    {
        Thread cur=Thread.currentThread();
        owner.compareAndSet(cur, null);
    }

}

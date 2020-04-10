package com.justbon.thread.whileLock;

/**
 * @author ganli
 * @version 1.0
 * @file SpinLockDemo.java
 * @Modified By：
 * @date 2020-04-10 下午2:10
 * @description
 */
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 * <p>
 * 线程AA首先进入MyLock()方法，输出 AA     come in ####
 * compareAndSet比较内存值是否为空，true，将内存值改为AA线程，A睡眠5秒线程
 * 线程BB进入MyLock()方法，输出 BB     come in ####
 * 此时compareAndSet，内存值为AA线程，while语句返回false，BB线程一直在此处判断，直到AA线程进入myUnLock()方法，将内存值重新改为null，输出 AA     invoke myUnLock()，AA线程运行结束
 * 线程BB进入myUnLock()方法，输出 BB     invoke myUnLock()，BB线程运行结束
 */
public class SpinLockDemo {

    public static void main(String[] args) {

        SpinLock2 spinLock = new SpinLock2();
        //AA线程
        new Thread(() -> {
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnLock();
        }, "AA").start();

        //BB线程 自旋等待AA使用完成
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            spinLock.myLock();
            spinLock.myUnLock();
        }, "BB").start();
    }


}
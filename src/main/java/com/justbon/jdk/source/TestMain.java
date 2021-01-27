package com.justbon.jdk.source;

/**
 * @author ganli
 * @version 1.0
 * @file TestMain.java
 * @Modified By：
 * @date 2021-01-27 上午9:52
 * @description
 * https://machen.blog.csdn.net/article/details/109758867#t6
 */
public class TestMain {


    public static void main(String[] args) {
        // 创建非公平锁 new ReentrantLock() 构造函数默认创建的是非公平锁 NonfairSync
        ReentrantLock lock = new ReentrantLock();
        /****
         *
         * FairSync、NonfairSync 代表公平锁和非公平锁
         * ，两者都是 ReentrantLock 静态内部类，只不过实现不同锁语义
         *
         * 公平锁 FairSync
         *
         * 公平锁是指多个线程按照申请锁的顺序来获取锁，线程直接进入队列中排队，队列中的第一个线程才能获得锁
         * 公平锁的优点是等待锁的线程不会饿死。缺点是整体吞吐效率相对非公平锁要低，
         * 等待队列中除第一个线程以外的所有线程都会阻塞，CPU 唤醒阻塞线程的开销比非公平锁大
         * 非公平锁 NonfairSync
         *
         * 非公平锁是多个线程加锁时直接尝试获取锁，获取不到才会到等待队列的队尾等待。
         * 但如果此时锁刚好可用，那么这个线程可以无需阻塞直接获取到锁
         * 非公平锁的优点是可以减少唤起线程的开销，整体的吞吐效率高，因为线程有几率不阻塞直接获得锁，
         * CPU 不必唤醒所有线程。缺点是处于等待队列中的线程可能会饿死，或者等很久才会获得锁
         */

        // 获取锁操作
        lock.lock();
        try {
            // 执行代码逻辑
        } catch (Exception ex) {
            // ...
        } finally {
            // 解锁操作
            lock.unlock();
        }
    }

}

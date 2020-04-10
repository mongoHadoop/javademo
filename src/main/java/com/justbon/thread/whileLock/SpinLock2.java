package com.justbon.thread.whileLock;

/**
 * @author ganli
 * @version 1.0
 * @file SpinLock2.java
 * @Modified By：
 * @date 2020-04-10 下午2:09
 * @description
 */
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock2 {

    /**
     * AtomicReference类提供了对引用对象的原子操作。
     * AtomicReference类持有一个对象，核心是CAS(Compare and Swap, 比较并交换）算法。
     * 对应于上面代码的compareAndSet方法。
     * CAS算法：CAS算法有3个操作数，当前值value，预期值expect，修改的新值update。当value与预期值expect相同时，将更新当前值value为新值update。
     */
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        //获取当前线程对象
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in ####");
        //当owner持有线程不为空时，循环等待
        while (!atomicReference.compareAndSet(null, thread)) {
            //当owner持有线程为空时，将owner持有线程设为当前线程，退出循环
        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        //执行完成后，将owner持有线程重新置为空，相当于释放锁
        System.out.println(Thread.currentThread().getName() + "\t invoke myUnLock()");
    }
}
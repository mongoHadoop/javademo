package com.justbon.thread.whileLock;

/**
 * @author ganli
 * @version 1.0
 * @file LockTest.java
 * @Modified By：
 * @date 2020-04-10 下午2:05
 * @description
 */

public class LockTest implements Runnable
{
    static int sum;
    private SpinLock lock;

    public LockTest(SpinLock lock)
    {
        this.lock=lock;
    }

    public static void main(String[] args) throws Exception
    {
        SpinLock lock=new SpinLock();
        for(int i=0;i<100;i++)
        {
            LockTest test=new LockTest(lock);
            Thread t=new Thread(test);
            t.start();
        }
        Thread.sleep(1000);
        System.out.println(sum);
    }

    @Override
    public void run()
    {
        this.lock.lock();
        sum++;
        this.lock.unlock();
    }

}

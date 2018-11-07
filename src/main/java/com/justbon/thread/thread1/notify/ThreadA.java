package com.justbon.thread.thread1.notify;

import java.util.List;

/**
 * Created by mongo on 2018/8/11.
 */
public class ThreadA extends Thread {

    private List list ;

    public ThreadA(List list){
        super();
        this.list = list;
    }


    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName());
        synchronized(list){
            try {
                if(list.size()!=5){
                    System.out.println("begin to wait ");
                    list.wait();//当前线程进入等待,释放锁 用一句话来总结 一下 wait 和 notify： wait 使线程停止运行， 而 notify使停止的线程继续运行。
                    System.out.println("ThredA list.size()  = "+list.size());
                    System.out.println("ThreadA 我知道了 list=5 我被唤醒了 ");
                    System.out.println("begin to wait is over ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA完毕 over ");
        }

    }

}

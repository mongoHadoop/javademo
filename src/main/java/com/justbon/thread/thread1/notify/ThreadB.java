package com.justbon.thread.thread1.notify;

import java.util.List;

/**
 * Created by mongo on 2018/8/11.
 */
public class ThreadB  extends  Thread{
    private List list ;

    public ThreadB(List list){
        super();
        this.list = list;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        synchronized (list) {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                if(list.size()==5){
                    list.notifyAll(); //notify 后线程不会马上停止，只是让其他线程进入准备状态，等当前线程运行完毕 退出线程，后其他线程才有机会执行
                    System.out.println("notify all ");
                }
                System.out.println("添加了" + (i + 1) + "个元素");
            }
        }
        System.out.println("ThreadA over ");

    }
}

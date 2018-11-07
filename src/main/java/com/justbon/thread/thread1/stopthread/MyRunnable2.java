package com.justbon.thread.thread1.stopthread;

/**
 * Created by mongo on 2018/8/11.
 */
public class MyRunnable2 implements Runnable{
    /**
     * 标准被停止 跳出 方法
     * 不过还是建议使用“抛异常”的方法来实现线程的停止，因为在catch块中还可以将异常向上抛，使线程停止的事件得以传播。
     */
    public void run(){
        try {
            for (int i = 0; i < 500000; i++) {
                if (Thread.currentThread().interrupted()) {
                    System.out.println("已经是停止状态了!我要退出了!");
                    throw new InterruptedException();// 抛出异常停止执行
                }
                System.out.println("i=" + (i + 1));
            }
            System.out.println("我在for下面");
        } catch (InterruptedException e) {
            System.out.println("进MyThread.java类run方法中的catch了！");
            e.printStackTrace();
        }

    }

}

package com.justbon.thread.thread1.stopthread;

/**
 * Created by mongo on 2018/8/11.
 */
public class MyRunnable implements Runnable{
    /**
     *
     */
    public void run(){
        for(int i=0;i<5000;i++){
            if(Thread.interrupted()){
                System.out.println("检测到停止命令。。。stop");
                break;
            }
            Thread.currentThread().setName("MyRunnable inter");
            System.out.println(  Thread.currentThread().getName() +" "+i);
        }
    }

}

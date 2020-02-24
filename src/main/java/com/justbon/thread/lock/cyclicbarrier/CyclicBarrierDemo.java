package com.justbon.thread.lock.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ganli
 * @version 1.0
 * @file CyclicBarrierDemo.java
 * @Modified By：
 * @date 2019-12-11 下午3:50
 * @description
 */
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable{
        private String soldierName;
        private final CyclicBarrier cyclicBarrier;
        public  Soldier(CyclicBarrier cyclicBarrier,String soldierName){
            this.cyclicBarrier=cyclicBarrier;
            this.soldierName=soldierName;
        }

        @Override
        public void run(){
            try {
                cyclicBarrier.await();
                work();
                cyclicBarrier.await();
            }catch (Exception e){

            }
        }
        void work(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(soldierName+"任务完成");

        }
    }

    public static class BarrierRun implements Runnable{

        boolean flag;
        int N;
        public BarrierRun(boolean flag,int N){
            this.flag=flag;
            this.N=N;
        }

        @Override
        public void run(){
                if(flag){
                    System.out.println("司令：［士兵"+N+"个],任务完成!");
                }else {
                    System.out.println("司令：［士兵"+N+"个],集合完成!");
                    flag=true;
                }
        }

    }

    public static void main(String[] args) {
        final int N=10;
        Thread[] allsoldier=new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N,new BarrierRun(flag,N));
        //设置屏障点，主要是为了执行这个方法
        System.out.println("士兵集结");
        for (int i=0;i<N;++i){
            System.out.println("士兵"+i+"报道！");
            allsoldier[i]=new Thread(new Soldier(cyclicBarrier,"士兵"+i));
            allsoldier[i].start();
        }
    }
}

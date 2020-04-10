package com.justbon.thread.furture;

/**
 * @author ganli
 * @version 1.0
 * @file Task.java
 * @Modified By：
 * @date 2020-03-05 下午2:27
 * @description
 */
import java.util.concurrent.Callable;

public class Task implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }

}

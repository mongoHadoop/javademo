package com.justbon.thread.furture;

/**
 * @author ganli
 * @version 1.0
 * @file CallableTest1.java
 * @Modified By：
 * @date 2020-03-05 下午2:28
 * @description 使用Callable+FutureTask获取执行结果
 *
 *
 * 实现Runnable接口和实现Callable接口的区别：
 *
 * 1、Runnable是自从java1.1就有了，而Callable是1.5之后才加上去的。
 *
 * 2、Callable规定的方法是call(),Runnable规定的方法是run()。
 *
 * 3、Callable的任务执行后可返回值，而Runnable的任务是不能返回值(是void)。
 *
 * 4、call方法可以抛出异常，run方法不可以。
 *
 * 5、运行Callable任务可以拿到一个Future对象，表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。通过Future对象可以了解任务执行情况，可取消任务的执行，还可获取执行结果。
 *
 * 6、加入线程池运行，Runnable使用ExecutorService的execute方法，Callable使用submit方法。
 *
 * 事实上，FutureTask是Future接口的一个唯一实现类。
 */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallableTest1 {

    public static void main(String[] args) {
        //第一种方式
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        executor.shutdown();

        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
//        Task task = new Task();
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//        Thread thread = new Thread(futureTask);
//        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            if(futureTask.get()!=null){
                System.out.println("task运行结果"+futureTask.get());
            }else{
                System.out.println("future.get()未获取到结果");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}
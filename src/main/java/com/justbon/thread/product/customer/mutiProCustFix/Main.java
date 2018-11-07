package com.justbon.thread.product.customer.mutiProCustFix;

/**
 * Created by mongo on 2018/8/11.
 * 多生产者对多消费者
 *  * notify 会通知到 同类型的 生产者或者消费者，造成相互等待，死锁
 * 改为notifyall
 */
public class Main {
    public static void main(String[] args) throws InterruptedException{
        String lock = new String();
        Customer customer = new Customer(lock);
        Product product = new Product(lock);

        ThreadP[] pThread = new ThreadP[2];
        ThreadC[] rThread = new ThreadC[2];

        for (int i = 0; i < 2; i++) {
            pThread[i] = new ThreadP(product);
            pThread[i].setName("生产者" + (i + 1));

            rThread[i] = new ThreadC(customer);
            rThread[i].setName("消费者" + (i + 1));

            pThread[i].start();
            rThread[i].start();
        }

        Thread.sleep(5000);
        Thread[] threadArray = new Thread[Thread.currentThread()
                .getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);

        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i].getName() + " "
                    + threadArray[i].getState());
        }
    }
}

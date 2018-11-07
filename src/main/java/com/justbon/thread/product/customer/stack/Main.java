package com.justbon.thread.product.customer.stack;

/**
 * Created by mongo on 2018/8/11.
 * 注意 在有多个消费者或者生产者时候，一定要用notifyALL 不然会出现死锁
 * 一个生产者对应多个消费者
 *  * notify 会通知到 同类型的 生产者或者消费者，造成相互等待，死锁
 * 改为notifyall
 */
public class Main {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        Product p = new Product(myStack);

        Customer r1 = new Customer(myStack);
        Customer r2 = new Customer(myStack);
        Customer r3 = new Customer(myStack);
        Customer r4 = new Customer(myStack);
        Customer r5 = new Customer(myStack);

        P_Thread pThread = new P_Thread(p);
        pThread.start();

        C_Thread cThread1 = new C_Thread(r1);
        C_Thread cThread2 = new C_Thread(r2);
        C_Thread cThread3 = new C_Thread(r3);
        C_Thread cThread4 = new C_Thread(r4);
        C_Thread cThread5 = new C_Thread(r5);
        cThread1.start();
        cThread2.start();
        cThread3.start();
        cThread4.start();
        cThread5.start();
    }
}

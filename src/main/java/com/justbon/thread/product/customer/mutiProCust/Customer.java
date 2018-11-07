package com.justbon.thread.product.customer.mutiProCust;

/**
 * Created by mongo on 2018/8/11.
 * 消费者
 */
public class Customer {

    private String lock;

    public  Customer(String lock){
        this.lock = lock;
    }
    public void getValue() {
        try {


            synchronized (lock) {
                while (ValueObject.value.equals("")) {
                    System.out.println("消费者 "
                            + Thread.currentThread().getName() + " WAITING了☆");
                    lock.wait();
                }
                System.out.println("消费者 " + Thread.currentThread().getName()
                        + " RUNNABLE了"+ValueObject.value);
                ValueObject.value = "";
                lock.notify();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

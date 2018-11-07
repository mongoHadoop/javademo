package com.justbon.thread.product.customer;

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
                if (ValueObject.value.equals("")) {
                    lock.wait();
                }
                System.out.println("get的值是" + ValueObject.value);
                ValueObject.value = "";
                lock.notify();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

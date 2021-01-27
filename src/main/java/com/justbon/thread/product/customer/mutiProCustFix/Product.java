package com.justbon.thread.product.customer.mutiProCustFix;

/**
 * Created by mongo on 2018/8/11.
 * 生产者
 */
public class Product {
    private String lock;
    public  Product(String lock){
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                while (!ValueObject.value.equals("")) {
                    System.out.println("生产者 "
                            + Thread.currentThread().getName() + " WAITING了★");
                    lock.wait();

                }

                System.out.println("生产者 " + Thread.currentThread().getName()
                        + " RUNNABLE了");
                String value = System.currentTimeMillis() + "_"
                        + System.nanoTime();
                ValueObject.value = value;
                lock="xxx";
                lock.notifyAll();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.justbon.thread.product.customer.mutiProCustFix;

/**
 * Created by mongo on 2018/8/11.
 */
public class ThreadC extends Thread {
    private Customer customer;

    public ThreadC(Customer customer){
        this.customer = customer;
    }

    @Override
    public void run() {
        while (true) {
            customer.getValue();
        }
    }
}

package com.justbon.thread.product.customer.mutiProCust;

/**
 * Created by mongo on 2018/8/11.
 */
public class ThreadP extends Thread {

    private Product product;
    public ThreadP(Product product){
        this.product = product;
    }
    public void run() {
        while (true) {
            product.setValue();
        }
    }
}

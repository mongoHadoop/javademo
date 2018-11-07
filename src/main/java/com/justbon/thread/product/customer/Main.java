package com.justbon.thread.product.customer;

/**
 * Created by mongo on 2018/8/11.
 * 这个是有BUG的多个生产者例子
 */
public class Main {
    public static void main(String[] args) {
        String lock = new String();
        Customer customer = new Customer(lock);
        Product product = new Product(lock);
        ThreadP tp = new ThreadP(product);
        ThreadC tc = new ThreadC(customer);

        tp.start();
        tc.start();
    }
}

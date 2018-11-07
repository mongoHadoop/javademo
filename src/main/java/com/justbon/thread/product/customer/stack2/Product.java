package com.justbon.thread.product.customer.stack2;


/**
 * Created by mongo on 2018/8/11.
 * 生产者
 */
public class Product {
    private MyStack myStack;

    public Product(MyStack myStack) {
        super();
        this.myStack = myStack;
    }

    public void pushService() {
        myStack.push();
    }
}

package com.justbon.thread.product.customer.stack;


/**
 * Created by mongo on 2018/8/11.
 * 消费者
 */
public class Customer {

        private MyStack myStack;

        public Customer(MyStack myStack) {
            super();
            this.myStack = myStack;
        }

        public void popService() {
            System.out.println("pop=" + myStack.pop());
        }
    }

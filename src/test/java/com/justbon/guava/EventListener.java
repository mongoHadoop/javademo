package com.justbon.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author ganli
 * @version 1.0
 * @file EventListener.java
 * @Modified By：
 * @date 2019-11-06 下午5:40
 * @description
 */
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}

package com.justbon.guava;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author ganli
 * @version 1.0
 * @file TestEventBus.java
 * @Modified By：
 * @date 2019-11-06 下午5:40
 * @description
 */
public class TestEventBus {
    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);
        new Thread(()->{
            try { Thread.sleep(1000);
                eventBus.post(new TestEvent(200));
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
        new Thread(()->{
            try {
                eventBus.post(new TestEvent(400));
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
        new Thread(()->{
            try { Thread.sleep(5000);
                eventBus.post(new TestEvent(300));
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
        new Thread(()->{
            try {
                Thread.sleep(5000);
                eventBus.post(new TestEvent(500));

            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();
        Thread.currentThread().join(1000);
        System.out.println("LastMessage:"+listener.getLastMessage());

    }
}

package com.justbon.guava;

/**
 * @author ganli
 * @version 1.0
 * @file TestEvent.java
 * @Modified By：
 * @date 2019-11-06 下午5:39
 * @description
 */
public class TestEvent {
    private final int message;
    public TestEvent(int message) {
        this.message = message;

        System.out.println("event message:"+message);
    }
    public int getMessage() {
        return message;
    }
}

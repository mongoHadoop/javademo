package com.justbon.designmodel.factory.bean;

import com.justbon.designmodel.factory.Human;

/**
 * @author ganli
 * @version 1.0
 * @file WhiteHuman.java
 * @Modified By：
 * @date 2019-05-13 上午8:29
 * @description
 * I'm glad to share my knowledge with you all.
 * 白色人种
 */
public class WhiteHuman implements Human {
    public void cry() {
        System.out.println("白色人种会哭");
    }
    public void laugh() {
        System.out.println("白色人种会大笑,侵略的笑声");
    }
    public void talk() {
        System.out.println("白色人种会说话,一般都是但是单字节!");
    }
}
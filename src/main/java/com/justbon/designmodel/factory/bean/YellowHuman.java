package com.justbon.designmodel.factory.bean;

/**
 * @author ganli
 * @version 1.0
 * @file YellowHuman.java
 * @Modified By：
 * @date 2019-05-13 上午8:29
 * @description
 * I'm glad to share my knowledge with you all.
 * 黄色人种,这个翻译的不准确,将就点吧
 */
import com.justbon.designmodel.factory.Human;

public class YellowHuman implements Human {
    public void cry() {
        System.out.println("黄色人种会哭");
    }
    public void laugh() {
        System.out.println("黄色人种会大笑,幸福呀!");
    }
    public void talk() {
        System.out.println("黄色人种会说话,一般说的都是双字节");
    }
}
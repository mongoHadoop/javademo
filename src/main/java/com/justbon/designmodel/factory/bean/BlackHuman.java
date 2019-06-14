package com.justbon.designmodel.factory.bean;

import com.justbon.designmodel.factory.Human;

/**
 * @author ganli
 * @version 1.0
 * @file BlackHuman.java
 * @Modified By：
 * @date 2019-05-13 上午8:30
 * @description 黑色人种,记得中学学英语,老师说black man是侮辱人的意思,不懂,没跟老外说话
 */
public class BlackHuman implements Human {
    public void cry() {
        System.out.println("黑人会哭");
    }
    public void laugh() {
        System.out.println("黑人会笑");
    }
    public void talk() {
        System.out.println("黑人可以说话,一般人听不懂");
    }
}
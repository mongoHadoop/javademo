package com.justbon.designmodel.strategy;

public class BackDoor implements IStrategy {
    @Override
    public void operate() {
        System.out.printf("找乔国老帮忙...让吴国太给孙权施加压力...");
    }
}

package com.justbon.designmodel.strategy;

public class GiveGreenLight implements IStrategy{
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯,放行...");
    }
}

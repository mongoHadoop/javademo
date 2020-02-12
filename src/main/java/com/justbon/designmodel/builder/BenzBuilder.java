package com.justbon.designmodel.builder;

import java.util.ArrayList;
/**
 * I'm glad to share my knowledge with you all.
 * 各种设施都给了,我们按照一定的顺序制造一个奔驰车
 */
public class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();
    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }
}

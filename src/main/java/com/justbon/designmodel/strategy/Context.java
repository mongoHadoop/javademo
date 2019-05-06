package com.justbon.designmodel.strategy;

/**
 * 3个策略具备了,需要一个装策略的锦囊
 */
public class Context {

    private IStrategy iStrategy;

    public Context(IStrategy iStrategy){
        this.iStrategy=iStrategy;
    }

    public void operate(){
        this.iStrategy.operate();
    }
}



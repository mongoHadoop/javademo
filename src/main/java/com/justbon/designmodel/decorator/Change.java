package com.justbon.designmodel.decorator;

/**
 * 　抽象装饰角色“七十二变”
 * @author lg
 *
 */
public class Change implements TheGreatestSage {
    private TheGreatestSage sage;
    
    public Change(TheGreatestSage sage){
        this.sage = sage;
    }
    @Override
    public void move() {
        // 代码
    	System.out.println("change ite ");
        sage.move();
    }

}

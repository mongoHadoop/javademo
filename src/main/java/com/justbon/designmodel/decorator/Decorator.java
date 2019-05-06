package com.justbon.designmodel.decorator;

/**
 * 　装饰角色
 * @author lg
 *
 */
public class Decorator implements Component{
    private Component component;
    
    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void sampleOperation() {
        // 委派给构件
    	//System.out.println("this is Decorator  add sampleOperation ");
        component.sampleOperation();
    }
    
}
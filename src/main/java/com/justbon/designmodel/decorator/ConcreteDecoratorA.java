package com.justbon.designmodel.decorator;

/**
 * 　具体装饰角色
 * @author lg
 *
 */
public class ConcreteDecoratorA extends Decorator {

	
    public ConcreteDecoratorA(Component component) {
        super(component);
    }
    
    @Override
    public void sampleOperation() {
    	System.out.println("this is ConcreteDecoratorA add sampleOperation ");
    	super.sampleOperation();
    }
}
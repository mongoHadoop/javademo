package com.justbon.designmodel.decorator;
/**
 * 　具体装饰角色
 * @author lg
 *
 */
public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }


    @Override
    public void sampleOperation() {
        // 委派给构件
        System.out.println("this is ConcreteDecoratorB  sampleOperation .. ");
        super.sampleOperation();
    }
}
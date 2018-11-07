package com.justbon.designmodel.decorator;
/**
 * 具体构件角色
 * @author lg
 *
 */
public class ConcreteComponent implements Component {

    @Override
    public void sampleOperation() {
    	System.out.println("this ConcreteComponent do sampleOperation  ");
    }

}
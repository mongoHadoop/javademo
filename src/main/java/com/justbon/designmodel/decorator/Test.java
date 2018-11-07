package com.justbon.designmodel.decorator;

public class Test {

	public static void main(String[] args) {
		Component obj1    =	new	ConcreteComponent();
		Component obA  =		new ConcreteDecoratorA(obj1);//new ConcreteDecoratorA(obj1);
		Component obB  =		new ConcreteDecoratorB(obj1);
		obA.sampleOperation();
	//	obB.sampleOperation();
	}

}

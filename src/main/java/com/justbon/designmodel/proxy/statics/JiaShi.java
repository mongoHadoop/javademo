package com.justbon.designmodel.proxy.statics;

/**
 * 
 *定义一个贾氏是什么样的人
 */
public class JiaShi implements KindWoman {

	@Override
	public void happyWithMan() {
		System.out.println("贾氏和男人在happy..");
		
	}

	@Override
	public void makeEyesWithMan() {
		System.out.println("贾氏抛媚眼...");
		
	}


}

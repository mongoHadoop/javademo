package com.justbon.thread.product.customer.stack2;


public class P_Thread extends Thread {

	private Product p;

	public P_Thread(Product p) {
		super();
		this.p = p;
	}

	@Override
	public void run() {
		while (true) {
			p.pushService();
		}
	}

}

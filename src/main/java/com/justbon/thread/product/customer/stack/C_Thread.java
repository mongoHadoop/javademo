package com.justbon.thread.product.customer.stack;


public class C_Thread extends Thread {

	private Customer r;

	public C_Thread(Customer r) {
		super();
		this.r = r;
	}

	@Override
	public void run() {
		while (true) {
			r.popService();
		}
	}

}

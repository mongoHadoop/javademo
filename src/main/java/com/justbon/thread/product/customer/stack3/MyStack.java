package com.justbon.thread.product.customer.stack3;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
	private List list = new ArrayList();

	synchronized public void push() {
		try {
			while (list.size() == 1) {
				System.out.println("生产者 wait" );
				this.wait();
			}
			double k = Math.random();
			System.out.println("生产者 push=" + k);
			list.add("anyString=" +k);
			this.notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public String pop() {
		String returnValue = "";
		try {
			/*if (list.size() == 0) {
				System.out.println("pop操作中的："
						+ Thread.currentThread().getName() + " 线程呈wait状态");
				this.wait();
			}*/
			//改成while 是反复检查，如果当当前线程被唤醒了，后 如果是 if 这不会 二次检查list，如果此时被唤醒 list等于了空 就可能 取不出来数据 出现错误
			while (list.size() == 0){
				System.out.println("pop操作中的："
						+ Thread.currentThread().getName() + " 线程呈wait状态");
				this.wait();
			}
			returnValue = "" + list.get(0);
			System.out.println("消费者 消费："
					+ returnValue);
			list.remove(0);
			this.notifyAll();
			System.out.println("pop=" + list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}

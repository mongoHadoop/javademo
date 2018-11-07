package com.justbon.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesSingleton extends Properties {

	private volatile static PropertiesSingleton instance = null;
	private static InputStream inputStream = null;

	private PropertiesSingleton() {
		inputStream = getClass().getResourceAsStream(
				"/ssosystem.config.properties");
	}

	public static PropertiesSingleton getInstance() {
		// 先检查实例是否存在，如果不存在才进入下面的同步块
		if (instance == null) {
			// 同步块，线程安全的创建实例
			synchronized (PropertiesSingleton.class) {
				// 再次检查实例是否存在，如果不存在才真正的创建实例
				if (instance == null) {
					instance = new PropertiesSingleton();
					try {
						instance.load(inputStream);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return instance;
	}

}
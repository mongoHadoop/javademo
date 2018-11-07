package com.justbon.util;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {

		 byte[] data="abc123!重中之重@#我".getBytes("UTF-8");
		for (byte t  :data)
			System.out.print(t);
		System.out.println("------------");
		System.out.println(new String(data,"utf-8"));
	}

}

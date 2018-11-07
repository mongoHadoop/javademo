package com.justbon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {

	/**
	 * @param args
	 */
	 private static String REGEX = "url=";
	    private static String INPUT = "http://10.5.110.236:8080/oa/jsp/main.jsp?url=ddddd&ddd=sfas撒飞洒付三阿斯顿发按时发多少发多少";
	    private static String REPLACE = "url2=";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(REGEX);
	       // get a matcher object
	       Matcher m = p.matcher(INPUT); 
	       if (m.find()) {
		       INPUT = m.replaceAll(REPLACE);
		       INPUT+="&url=xxdfsdfsdfs";
		       System.out.println(INPUT);
	       }
	}

}

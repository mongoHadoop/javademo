package com.justbon.util.encryp;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Test {  
	  
    public static void main(String[] args) throws IOException {  
        BASE64Encoder encoder = new BASE64Encoder();  
        BASE64Decoder decoder = new BASE64Decoder();  
        String s = "我啥地方大师傅是";  
        String encoded = encoder.encode(s.getBytes("UTF-8"));  
        System.out.println("Xue -> " + encoded+"==="+new String(decoder.decodeBuffer(encoded),"UTF-8"));  
          
        s = "Xu";
        encoded = encoder.encode(s.getBytes());  
        System.out.println("Xu -> " + encoded);  
          
        s = "X";  
        encoded = encoder.encode(s.getBytes());  
        System.out.println("X -> " + encoded);  
        
        
    }  
}  
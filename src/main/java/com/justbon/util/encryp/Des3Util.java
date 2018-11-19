package com.justbon.util.encryp;


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


import com.justbon.util.DateUtils;
import sun.misc.BASE64Encoder;


public class Des3Util {
    public static void main(String[] args) throws Exception {
        String key1 = "1$2%3^u(&6R$yh&N";
        String iv = "#$y45G,H";
        
    	byte[] key =  GetKeyBytes(key1);
        byte[] keyiv = iv.substring(0,8).getBytes("UTF-8");
        
        byte[] data="abc123!@#我".getBytes("UTF-8");
/*        
        System.out.println("ECB加密解密");
        byte[] str3 = des3EncodeECB(key,data );
        byte[] str4 = ees3DecodeECB(key, str3);
        System.out.println(new BASE64Encoder().encode(str3));
        System.out.println(new String(str4, "UTF-8"));
        System.out.println();*/
        System.out.println("CBC加密解密");
        byte[] str5 = des3EncodeCBC(key, keyiv, data);
        System.out.println(new String(str5));
        
        byte[] str6 = des3DecodeCBC(key, keyiv, str5);
        String code =  new BASE64Encoder().encode(str5);

		System.out.println(code);
        System.out.println(code.equals("nwGEPNrhNoZ1XkyTM4BzzA=="));
        System.out.println(new String(str6, "UTF-8"));
        
        
		
		String timer1 = DateUtils.getCurrDateTimeStrSSS();
		System.out.println(timer1);
		String userIDCard = "abc123!@#我"+"|"+timer1;
		
		byte[] strIdcard = Des3Util.des3EncodeCBC(key, keyiv, userIDCard.getBytes("UTF-8"));
        String idCode =  new BASE64Encoder().encode(strIdcard);
		System.out.println("usercard : " + idCode);
    }
    

  //计算24位长的密码byte值,再用前8位数据对应补全后8位    
    public static byte[] GetKeyBytes(String strKey) throws Exception {    
        if (null == strKey || strKey.length() < 1)    
            throw new Exception("key is null or empty!");    
        byte[] bkey = strKey.getBytes();    
        int start = bkey.length;    
        byte[] bkey24 = new byte[24];    
        for (int i = 0; i < start; i++) {    
            bkey24[i] = bkey[i];    
        }    
        for (int i = start; i < 24; i++) {//为了与.net16位key兼容    
            bkey24[i] = bkey[i - start];    
        }    
        return bkey24;    
    }    

    /**
     * ECB加密,不要IV
     * @param key 密钥
     * @param data 明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public static byte[] des3EncodeECB(byte[] key, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    /**
     * ECB解密,不要IV
     * @param key 密钥
     * @param data Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    public static byte[] ees3DecodeECB(byte[] key, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    /**
     * CBC加密
     * @param key 密钥
     * @param keyiv IV
     * @param data 明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    /**
     * CBC解密
     * @param key 密钥
     * @param keyiv IV
     * @param data Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
}

package com.justbon.util.encryp;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author ganli
 * @version 1.0
 * @file GDEncrypRSA.java
 * @Modified By：
 * @date 2020-06-11 下午1:57
 * @description 光大银行的加密班
 */
public class GDEncrypRSA {

    /**
     * 定义加密方式
     */
    private final static String KEY_RSA = "RSA";
    /**
     * 加密
     * @param publicKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        if(publicKey!=null){
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }
    static PrivateKey toPrivateKey(byte[] b)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(b);
        // 指定的加密算法
        KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
        // 取私钥对象
        return factory.generatePrivate(pkcs);
    }
    static PublicKey toPublicKey(byte[] b) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(b);
        KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
        return factory.generatePublic(keySpec);
    }
    /**
     * 加密
     * @param privateKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte[] encrypt(RSAPrivateKey privateKey, byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        if(privateKey!=null){
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * 解密
     * @param privateKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte[] decrypt(RSAPrivateKey privateKey,byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        if(privateKey!=null){
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * 解密
     * @param publicKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte[] decrypt(RSAPublicKey publicKey,byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        if(publicKey!=null){
            //Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA");
            //根据公钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        GDEncrypRSA rsa = new GDEncrypRSA();
        String msg = "郭XX-精品相声";
        //KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        //初始化密钥对生成器，密钥大小为1024位
        keyPairGen.initialize(1024);
        //生成一个密钥对，保存在keyPair中MD5withRSA
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        String privateKeyStr=  Base64.encodeBase64String(privateKey.getEncoded());
        //得到公钥 并做base64编码
        System.out.println("privateKeyStr:"+privateKeyStr);
        //得到公钥
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        String pubKeyStr=  Base64.encodeBase64String(publicKey.getEncoded());
        //得到公钥 并做base64编码
        System.out.println("pubKeyStr:"+pubKeyStr);

        //用公钥加密
        byte[] srcBytes = msg.getBytes("UTF-8");
        byte[] resultBytes = rsa.encrypt(privateKey, srcBytes);
        //用私钥解密
        byte[] decBytes = rsa.decrypt(publicKey, resultBytes);

        System.out.println("明文是:" + msg);
        System.out.println("加密后是:" + Base64.encodeBase64String(resultBytes));
        System.out.println("解密后是:" + new String(decBytes));

    }
}

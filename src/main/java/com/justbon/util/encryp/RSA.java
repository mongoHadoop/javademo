package com.justbon.util.encryp;

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSA {
    /**
     * 定义加密方式
     */
    private final static String KEY_RSA = "RSA";
    /**
     * 定义签名算法
     */
    private final static String KEY_RSA_SIGNATURE = "MD5withRSA";
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
    // 用私钥对data签名
    public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {
        Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
        signature.initSign(toPrivateKey(privateKey));
        signature.update(data);
        return signature.sign();
    }
    // 公钥解密数据
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign)
            throws Exception {
        Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
        signature.initVerify(toPublicKey(publicKey));
        signature.update(data);
        return signature.verify(sign);
    }
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key)
            throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_RSA);
        cipher.init(Cipher.DECRYPT_MODE, toPrivateKey(key));
        return cipher.doFinal(data);
    }
    public static byte[] decryptByPublicKey(byte[] data, byte[] key)
            throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_RSA);
        cipher.init(Cipher.DECRYPT_MODE, toPublicKey(key));
        return cipher.doFinal(data);
    }
    public static byte[] encryptByPublicKey(byte[] data, byte[] key)
            throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, toPublicKey(key));
        return cipher.doFinal(data);
    }
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key)
            throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, toPrivateKey(key));
        return cipher.doFinal(data);

    }
    static String tos(byte[] b) {
        String ans = "";
        for (int i = 0; i < b.length; i++) {
            ans += String.format("%02X", b[i]);
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_RSA);
        generator.initialize(1024);//默认为1024
/*        SecureRandom secrand = new SecureRandom();
        secrand.setSeed("tmriPayment".getBytes()); // 初始化随机产生器*/

        KeyPair keyPair = generator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        System.out.println("公钥:" + Base64.encodeBase64String(publicKey.getEncoded()));
        System.out.println("私钥:" + Base64.encodeBase64String(privateKey.getEncoded()));
        String str="你好，世界！";
        byte[] data = str.getBytes();

        System.out.println("公钥加密--------------私钥解密");
        byte[] enc = encryptByPublicKey(data, publicKey.getEncoded());
        System.out.println("加密前:" + new String(data));
        System.out.println("加密后:" + tos(enc));
        byte[] dec = decryptByPrivateKey(enc, privateKey.getEncoded());
        System.out.println("私钥解密后:" + new String(dec));
        System.out.println("--------------------------------------------------");

        System.out.println("私钥加密----------------------公钥解密");
        enc = encryptByPrivateKey(data, privateKey.getEncoded());
        dec = decryptByPublicKey(enc, publicKey.getEncoded());
        System.out.println("加密前:" + new String(data));
        System.out.println("加密后:" + tos(enc));
        System.out.println("公钥解密后:" + new String(dec));

        System.out.println("私钥签名--------公钥验证签名");
        byte[] sign = sign(data, privateKey.getEncoded());
        String sing64=Base64.encodeBase64String(sign);
        byte[] decodesign64=Base64.decodeBase64(sing64);
        boolean status = verify(data, publicKey.getEncoded(), decodesign64);
        System.out.println("签名:" + tos(sign));
        System.out.println("状态:" + status);
    }
}
package com.justbon.util.encryp;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
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
        // generator.initialize(1024);//默认为1024
        KeyPair keyPair = generator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        System.out.println("公钥:" + tos(publicKey.getEncoded()));
        System.out.println("私钥:" + tos(privateKey.getEncoded()));
        byte[] data = "你好，世界！".getBytes();
        System.out.println("公钥加密-------私钥解密");
        byte[] enc = encryptByPublicKey(data, publicKey.getEncoded());
        byte[] dec = decryptByPrivateKey(enc, privateKey.getEncoded());
        System.out.println("加密前:" + tos(data));
        System.out.println("解密后:" + new String(dec));
        System.out.println("私钥加密--------公钥解密");
        enc = encryptByPrivateKey(data, privateKey.getEncoded());
        dec = decryptByPublicKey(enc, publicKey.getEncoded());
        System.out.println("加密前:" + tos(data));
        System.out.println("解密后:" + new String(dec));
        System.out.println("私钥签名--------公钥验证签名");
        byte[] sign = sign(data, privateKey.getEncoded());
        boolean status = verify(data, publicKey.getEncoded(), sign);
        System.out.println("签名:" + tos(sign));
        System.out.println("状态:" + status);
    }
}
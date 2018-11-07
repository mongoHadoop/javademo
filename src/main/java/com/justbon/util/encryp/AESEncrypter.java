package com.justbon.util.encryp;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qq 1515308
 */
public class AESEncrypter {
	public static void main(String[] args) throws Exception {
		String encryptAsString = new AESEncrypter().encrypt("qq:1515308");
		System.out.println(encryptAsString);
		String decryptAsString = new AESEncrypter()
				.decryptAsString(encryptAsString);
		System.out.println(decryptAsString);
	}

	private static String aesKeyStr = "NGQxNmUwMjM4M2Y0MTI2MTM3NDI0Y2MxMjA1N2IyNDM=";

	private SecretKey aesKey;

	private AESEncrypter() {
		aesKey = loadAesKey();
	}

	private AESEncrypter(String aes) {
		aesKey = loadAesKey(aes);
	}

	private static AESEncrypter INSTANCE;

	private static Map<String, AESEncrypter> INSTANCES = new HashMap<String, AESEncrypter>();

	public static AESEncrypter getInstance() {
		if (INSTANCE == null) {
			synchronized (aesKeyStr) {
				if (INSTANCE == null) {
					INSTANCE = new AESEncrypter();
				}
			}
		}
		return INSTANCE;
	}

	public static AESEncrypter getInstance(String aes) {
		if (INSTANCES.get(aes) == null) {
			synchronized (aesKeyStr) {
				if (INSTANCES.get(aes) == null) {
					INSTANCES.put(aes, new AESEncrypter(aes));
				}
			}
		}
		return INSTANCES.get(aes);
	}

	public String encrypt(String msg) throws Exception {
		try {
			Cipher ecipher = Cipher.getInstance("AES");
			ecipher.init(Cipher.ENCRYPT_MODE, aesKey);
			return HexUtil.encodeHexStr(ecipher.doFinal(msg.getBytes()));
		} catch (Exception e) {
			String errMsg = "decrypt error, data:" + msg;
			throw new Exception(errMsg, e);
		}
	}

	public byte[] decrypt(String msg) throws Exception {
		try {
			Cipher dcipher = Cipher.getInstance("AES");
			dcipher.init(Cipher.DECRYPT_MODE, aesKey);
			return dcipher.doFinal(msg.getBytes());
		} catch (Exception e) {
			String errMsg = "decrypt error, data:" + msg;
			throw new Exception(errMsg, e);
		}
	}

	public String decryptAsString(String msg) throws Exception {
		return new String(this.decrypt(msg));
	}

	private static SecretKey loadAesKey() {
		String buffer = new String(Base64.decodeBase64(aesKeyStr));
		byte[] keyStr =buffer.getBytes();
		SecretKeySpec aesKey = new SecretKeySpec(keyStr, "AES");
		return aesKey;
	}

	private static SecretKey loadAesKey(String aesKeyStr) {
		String buffer = new String(Base64.decodeBase64(aesKeyStr));
		byte[] keyStr =buffer.getBytes();
		SecretKeySpec aesKey = new SecretKeySpec(keyStr, "AES");
		return aesKey;
	}
}
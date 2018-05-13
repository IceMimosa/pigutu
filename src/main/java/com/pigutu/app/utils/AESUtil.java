package com.pigutu.app.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES加密工具
 * 
 * @author guoyuanzhuang@gmail.com
 * @Description: 
 */
public class AESUtil {

	private static String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
	private static String CHAR_ENCODING = "UTF-8";

//	public static String AES_KEY = "89725ab9a9bf4c86";


	public static byte[] encrypt(byte[] data, byte[] key) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM);// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, seckey);// 初始化
			byte[] result = cipher.doFinal(data);
			return result; // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static byte[] decrypt(byte[] data, byte[] key) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, seckey);// 初始化
			byte[] result = cipher.doFinal(data);
			return result; // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(String data, String key) {
		try {
			byte[] valueByte = encrypt(data.getBytes(CHAR_ENCODING),
					key.getBytes(CHAR_ENCODING));
			return new String(Base64.encode(valueByte, Base64.DEFAULT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String data, String key) {
		try {
			byte[] originalData = Base64.decode(data.getBytes(), Base64.DEFAULT);
			byte[] valueByte = decrypt(originalData,
					key.getBytes(CHAR_ENCODING));
			return new String(valueByte, CHAR_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String genarateRandomKey() {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			keygen.init(128);
			Key key = keygen.generateKey();
			byte[] bytes = key.getEncoded();

			return new String(parseByte2HexStr(bytes)); //Base64.encode(bytes, Base64.DEFAULT)
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

}

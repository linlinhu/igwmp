package com.emin.wxs.util;

import java.security.MessageDigest;

public class EncryptUtil {
	
	private static final String ALGORITHM_MD5 = "MD5";
	private static final String ALGORITHM_SHA1 = "SHA1";
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String encryptMD5(String str) {
		return encrypt(ALGORITHM_MD5,str);
	}
	public static String encryptSHA1(String str) {
		return encrypt(ALGORITHM_SHA1,str);
	}
	public static String encrypt(String algorithm,String str) {
		if (str == null) return null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes("UTF-8"));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int i = 0; i < len; i++) {
			buf.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return buf.toString();
	}
	
	public static String encode(String algorithm, String str) {
		
		return encrypt(algorithm, str);
	}
}

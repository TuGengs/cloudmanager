package com.halfmoon.cloudmanager.util.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类，将字符串使用MD5加密算法加密
 * @author hzq
 *
 */
public class MD5Util {

	public static String encrypt(String str) {

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(str.getBytes());
			
			// 返回32位加密字符串
			return new BigInteger(1, md.digest()).toString(32);

		} catch (NoSuchAlgorithmException e) {
			return null; // ignore
		}

	}

}

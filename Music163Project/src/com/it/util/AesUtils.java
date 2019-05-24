package com.it.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES逆向思维破解类
 * AES对原数据加密以后,使用BASE64进行编码转化
 * */
public class AesUtils {
	/**
	 * 加密
	 * @param src			明文(没有加密的数据)
	 * @param eFormat		字符集
	 * @param key			密钥
	 * @param ivParameter 	IvParameterSpec需要的向量
	 * */
	public static String Enrypt(String src,String eFormat,String key,String ivParameter) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
			//使用Cipher 进行AES加密
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//将key转换成二进制
			byte[] raw = key.getBytes();
			SecretKeySpec specKey = new SecretKeySpec(raw, "AES");
			//使用CBC模式，需要一个数据列表iv,可增加算法强度
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, specKey,iv);
			byte[] encrypted = cipher.doFinal(src.getBytes(eFormat));
			//使用BASE64转码并返回加密  数据
			return new BASE64Encoder().encode(encrypted);
	}
	/**
	 * 解密
	 * @param src			明文(没有加密的数据)
	 * @param eFormat		字符集
	 * @param key			密钥
	 * @param ivParameter 	IvParameterSpec需要的向量
	 * */
	public static String decrypt(String src,String eFormat,String key,String ivParameter){
		try {
			//使用ASCII进行解密
			byte[] raw = key.getBytes("ASCII");
			SecretKeySpec specKey = new SecretKeySpec(raw,"AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, specKey,iv);
			byte[] encrypted = new BASE64Decoder().decodeBuffer(src);
			byte[] original = cipher.doFinal(encrypted);
			return new String(original,eFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
}

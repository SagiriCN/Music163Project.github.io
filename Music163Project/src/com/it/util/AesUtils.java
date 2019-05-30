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
 * AES����˼ά�ƽ���
 * AES��ԭ���ݼ����Ժ�,ʹ��BASE64���б���ת��
 * */
public class AesUtils {
	/**
	 * ����
	 * @param src			����(û�м��ܵ�����)
	 * @param eFormat		�ַ���
	 * @param key			��Կ
	 * @param ivParameter 	IvParameterSpec��Ҫ������
	 * */
	public static String Enrypt(String src,String eFormat,String key,String ivParameter) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
			//ʹ��Cipher ����AES����
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//��keyת���ɶ�����
			byte[] raw = key.getBytes();
			SecretKeySpec specKey = new SecretKeySpec(raw, "AES");
			//ʹ��CBCģʽ����Ҫһ�������б�iv,�������㷨ǿ��
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, specKey,iv);
			byte[] encrypted = cipher.doFinal(src.getBytes(eFormat));
			//ʹ��BASE64ת�벢���ؼ���  ����
			return new BASE64Encoder().encode(encrypted);
	}
	/**
	 * ����
	 * @param src			����(û�м��ܵ�����)
	 * @param eFormat		�ַ���
	 * @param key			��Կ
	 * @param ivParameter 	IvParameterSpec��Ҫ������
	 * */
	public static String decrypt(String src,String eFormat,String key,String ivParameter){
		try {
			//ʹ��ASCII���н���
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

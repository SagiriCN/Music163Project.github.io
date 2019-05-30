package com.it.wyy;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.it.entity.WyyParams;
import com.it.util.AesUtils;
/**
 * 抓取网易云类
 * */
public class WyySpider {

	//AES加密偏移量
	private static String iv = "0102030405060708";
	//密钥
	private static String g="0CoJUm6Qyw8W8jud";
	private static String i="Sx7KnWt4ttr85X0b";
	private static String encSecKey="252e2abdc25a5c8e4e4131b88db3df7d01ab4a139249b78e653b97ab52f53b873993b86648e54daa3a99eeb20fd3b2c4d1d551231a152bfa56ed0a13baae9243f978bf1fbcde4e70b25087fd0aeef413a698a0a37567a550876f8cdeedb25cf359f54532eb2681f63641a4fa98b837fb9978c3296b2923bca8f5d1661d3ec5dc";
	
	@SuppressWarnings("unused")
	private static String a(int a){
		int d,e;
		String b = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String c = null;
		for (d=0;a>d;d++) {
			e = (int)Math.floor(Math.random()*b.length());
			c += b.charAt(e);
		}
		return c;
	}
	
	private static String b(String d,String g) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		return AesUtils.Enrypt(d, "utf-8", g, iv).replace("\r\n", "");
	}
	
	public static WyyParams getParams(String d) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		//		测试数据
		System.out.println(d);
		String encText = b(d,g); 
		encText = b(encText,i);
		WyyParams params = new WyyParams();
		params.setEncSecKey(encSecKey);
		params.setEncText(encText);
		//		测试数据
		//		System.out.println(encSecKey);
		//		System.out.println(encText);
		return params;
	}
	
	/**
	 * @param url	      查询连接
	 * @param urlData 查询数据
	 * */
	public static Document spider(String url,String urlData){
		Document doc = null;
		Map<String, String> data=new HashMap<String, String>();
        Map<String, String> head=new HashMap<String, String>();
        try {
            WyyParams params=WyySpider.getParams(urlData);
            Connection con=Jsoup.connect(url);
            //装载所需密钥
            data.put("encSecKey",params.getEncSecKey());
            data.put("params",params.getEncText());
            //设置头部,模拟浏览器
            head.put("Host","music.163.com");
            head.put("Content-Type","application/x-www-form-urlencoded");
            head.put("Accept","*/*");
            con.headers(head);
            con.data(data);
            //执行
            doc= con.post();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return doc;
	}
	
    
	
	
}

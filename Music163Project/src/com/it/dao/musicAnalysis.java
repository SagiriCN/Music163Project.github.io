package com.it.dao;
import org.json.JSONArray;
import org.json.JSONObject;
import com.it.impldao.musicAnalysisImpl;
import com.it.wyy.WyySpider;
/**
 * ���ֵ�ַ������
 * {
 * ��ѯ����URI "https://music.163.com/weapi/cloudsearch/get/web?csrf_token="
 * ��ѯ����Data "{\"csrf_token\":\"hlpretag=\",\"hlposttag\":\"\",\"s\":\"%s\",\"type\":\"1\",\"offset\":\"0\",\"total\":\"true\",\"limit\":\"1\"}"
 * �̶����� csrf_token	hlposttag
 * 
 * s:����/�������� 
 * type:��������[
 * 		1:����
 * 		10:ר��
 * 		100:����
 * 		1000:�赥
 * 		1002:�û�
 * 		1004:mv
 * 		1006:���
 * 		1009:������̨
 * ]
 *  	offset:ƫ����(��ҳ��)
 * 		limit:��ȡ����
 * }
 * 
 * 
 * {
 * ��ȡ������Ϣ "https://music.163.com/weapi/cloudsearch/get/web?csrf_token="
 * ����json {\"csrf_token\":\"hlpretag=\",\"hlposttag\":\"\",\"s\":\"%s\",\"type\":\"1\",\"offset\":\"0\",\"total\":\"true\",\"limit\":\"1\"}
 * 
 * 
 * ��ȡ����https://music.163.com/#/song?id=28828076
 * }
 * 
 * */
public class musicAnalysis implements musicAnalysisImpl{
	
	
	public JSONArray QuerySong(String name,int limit) {
		//�����Ƹ�����ϢAPI�ӿ�
		String url = "https://music.163.com/weapi/cloudsearch/get/web?csrf_token=";
		//��������
		String urlData = String.format("{\"csrf_token\":\"hlpretag=\",\"hlposttag\":\"\",\"s\":\"%s\",\"type\":\"1\",\"offset\":\"0\",\"total\":\"true\",\"limit\":\"%s\"}", name,limit);
		//��ȡ���������ַ��ص�json����
		String JsonValue = WyySpider.spider(url, urlData).body().toString().replace("<body>", "").replace("</body>", "");
		JSONArray MusicJsonArray = new JSONArray();
		
		try {
			//��ȡ������������
			JSONArray songArray = new JSONObject(JsonValue).getJSONObject("result").getJSONArray("songs");
			for (int i = 0; i < songArray.length(); i++) {
				JSONObject MusicJson = new JSONObject();
				//��ȡ����Id
				System.out.print("����ID:"+songArray.getJSONObject(i).getString("id"));
				//��ȡ��������
				System.out.print("  ��������:"+songArray.getJSONObject(i).getString("name"));
				//��ȡ����Id
				System.out.print("  ����ID:"+songArray.getJSONObject(i).getJSONArray("ar").getJSONObject(0).getString("id"));
				//��ȡ��������
				System.out.print("  ��������:"+songArray.getJSONObject(i).getJSONArray("ar").getJSONObject(0).getString("name"));
				//��ȡ����ר������
				System.out.print("  ר������:"+songArray.getJSONObject(i).getJSONObject("al").getString("picUrl"));
				//��ȡ����ר������
				System.out.println("  ר������:"+songArray.getJSONObject(i).getJSONObject("al").getString("name"));
				//����id
				MusicJson.append("MusicId",songArray.getJSONObject(i).getString("id"));
				//��������
				MusicJson.append("MusicName",songArray.getJSONObject(i).getString("name"));
				System.out.println(songArray.getJSONObject(i).getString("name"));
				//����Id
				MusicJson.append("AuthorId",songArray.getJSONObject(i).getJSONArray("ar").getJSONObject(0).getString("id"));
				//��������
				MusicJson.append("AuthorName",songArray.getJSONObject(i).getJSONArray("ar").getJSONObject(0).getString("name"));
				//ר����������
				MusicJson.append("AlbumPicUrl",songArray.getJSONObject(i).getJSONObject("al").getString("picUrl"));
				//ר������
				MusicJson.append("AlbumName",songArray.getJSONObject(i).getJSONObject("al").getString("name"));
				//ר��Id
				MusicJson.append("AlbumId",songArray.getJSONObject(i).getJSONObject("al").getString("id"));
				MusicJsonArray.put(MusicJson);
			}
			return MusicJsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		/*
		 //����������/id��������
			JSONObject songs = new JSONObject(JsonValue).getJSONObject("result").getJSONArray("songs").getJSONObject(0);
			//����ר������/id/�����ַ
			JSONObject al = songs.getJSONObject("al");
			//��ȡר������
			String AlbumName = al.getString("name");
			//��ȡר��id
			String AlbumId = al.getString("id");
			//��ȡר�������ַ
			String AlbumPicUrl = al.getString("picUrl");
			//��ȡ��������
			String MusicName = songs.getString("name");
			//��ȡ����id
			String MusicId = songs.getString("id");
			//��������
			//System.out.println(MusicId);
			//װ����Ҫ���ص�json�ļ�
			JSONObject MusicJson = new JSONObject();
			MusicJson.append("MusicName", MusicName);	  //װ����������
			MusicJson.append("MusicId", MusicId);	 	  //װ������id
			MusicJson.append("AlbumName", AlbumName);	  //װ��ר������
			MusicJson.append("AlbumId", AlbumId);	 	  //װ��ר��id
			MusicJson.append("AlbumPicUrl", AlbumPicUrl); //װ��ר��ͼƬ����
			
			
			
			//��������
			    
			
		 * */
	}
}

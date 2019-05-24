package com.it.dao;
import org.json.JSONArray;
import org.json.JSONObject;
import com.it.impldao.musicAnalysisImpl;
import com.it.wyy.WyySpider;
/**
 * 音乐地址分析类
 * {
 * 查询歌曲URI "https://music.163.com/weapi/cloudsearch/get/web?csrf_token="
 * 查询歌曲Data "{\"csrf_token\":\"hlpretag=\",\"hlposttag\":\"\",\"s\":\"%s\",\"type\":\"1\",\"offset\":\"0\",\"total\":\"true\",\"limit\":\"1\"}"
 * 固定属性 csrf_token	hlposttag
 * 
 * s:歌曲/歌手名称 
 * type:搜索类型[
 * 		1:歌曲
 * 		10:专辑
 * 		100:歌手
 * 		1000:歌单
 * 		1002:用户
 * 		1004:mv
 * 		1006:歌词
 * 		1009:主播电台
 * ]
 *  	offset:偏移量(分页用)
 * 		limit:获取数量
 * }
 * 
 * 
 * {
 * 获取歌曲信息 "https://music.163.com/weapi/cloudsearch/get/web?csrf_token="
 * 所需json {\"csrf_token\":\"hlpretag=\",\"hlposttag\":\"\",\"s\":\"%s\",\"type\":\"1\",\"offset\":\"0\",\"total\":\"true\",\"limit\":\"1\"}
 * 
 * 
 * 获取评论https://music.163.com/#/song?id=28828076
 * }
 * 
 * */
public class musicAnalysis implements musicAnalysisImpl{
	
	
	public JSONArray QuerySong(String name,int limit) {
		//网易云歌曲信息API接口
		String url = "https://music.163.com/weapi/cloudsearch/get/web?csrf_token=";
		//所需数据
		String urlData = String.format("{\"csrf_token\":\"hlpretag=\",\"hlposttag\":\"\",\"s\":\"%s\",\"type\":\"1\",\"offset\":\"0\",\"total\":\"true\",\"limit\":\"%s\"}", name,limit);
		//获取网易云音乐返回的json数据
		String JsonValue = WyySpider.spider(url, urlData).body().toString().replace("<body>", "").replace("</body>", "");
		JSONArray MusicJsonArray = new JSONArray();
		
		try {
			//获取所有所需数组
			JSONArray songArray = new JSONObject(JsonValue).getJSONObject("result").getJSONArray("songs");
			for (int i = 0; i < songArray.length(); i++) {
				JSONObject MusicJson = new JSONObject();
				//获取音乐Id
				System.out.print("音乐ID:"+songArray.getJSONObject(i).getString("id"));
				//获取音乐名称
				System.out.print("  音乐名称:"+songArray.getJSONObject(i).getString("name"));
				//获取作者Id
				System.out.print("  作者ID:"+songArray.getJSONObject(i).getJSONArray("ar").getJSONObject(0).getString("id"));
				//获取作者名称
				System.out.print("  作者名称:"+songArray.getJSONObject(i).getJSONArray("ar").getJSONObject(0).getString("name"));
				//获取音乐专辑封面
				System.out.print("  专辑封面:"+songArray.getJSONObject(i).getJSONObject("al").getString("picUrl"));
				//获取音乐专辑名称
				System.out.println("  专辑名称:"+songArray.getJSONObject(i).getJSONObject("al").getString("name"));
				//音乐id
				MusicJson.append("MusicId",songArray.getJSONObject(i).getString("id"));
				//音乐名称
				MusicJson.append("MusicName",songArray.getJSONObject(i).getString("name"));
				System.out.println(songArray.getJSONObject(i).getString("name"));
				//作者Id
				MusicJson.append("AuthorId",songArray.getJSONObject(i).getJSONArray("ar").getJSONObject(0).getString("id"));
				//作者名称
				MusicJson.append("AuthorName",songArray.getJSONObject(i).getJSONArray("ar").getJSONObject(0).getString("name"));
				//专辑封面连接
				MusicJson.append("AlbumPicUrl",songArray.getJSONObject(i).getJSONObject("al").getString("picUrl"));
				//专辑名称
				MusicJson.append("AlbumName",songArray.getJSONObject(i).getJSONObject("al").getString("name"));
				//专辑Id
				MusicJson.append("AlbumId",songArray.getJSONObject(i).getJSONObject("al").getString("id"));
				MusicJsonArray.put(MusicJson);
			}
			return MusicJsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		/*
		 //逐层查找名称/id所在数组
			JSONObject songs = new JSONObject(JsonValue).getJSONObject("result").getJSONArray("songs").getJSONObject(0);
			//查找专辑名称/id/封面地址
			JSONObject al = songs.getJSONObject("al");
			//获取专辑名称
			String AlbumName = al.getString("name");
			//获取专辑id
			String AlbumId = al.getString("id");
			//获取专辑封面地址
			String AlbumPicUrl = al.getString("picUrl");
			//获取音乐名称
			String MusicName = songs.getString("name");
			//获取音乐id
			String MusicId = songs.getString("id");
			//测试数据
			//System.out.println(MusicId);
			//装载需要返回的json文件
			JSONObject MusicJson = new JSONObject();
			MusicJson.append("MusicName", MusicName);	  //装载音乐名称
			MusicJson.append("MusicId", MusicId);	 	  //装载音乐id
			MusicJson.append("AlbumName", AlbumName);	  //装载专辑名称
			MusicJson.append("AlbumId", AlbumId);	 	  //装载专辑id
			MusicJson.append("AlbumPicUrl", AlbumPicUrl); //装载专辑图片连接
			
			
			
			//测试数据
			    
			
		 * */
	}
}

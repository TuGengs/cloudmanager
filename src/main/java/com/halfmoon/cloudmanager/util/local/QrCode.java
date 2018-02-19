package com.halfmoon.cloudmanager.util.local;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.google.gson.JsonObject;

public class QrCode {
	
	private static final String URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	private static String ticket_url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
	
	public static String getUrl(int id){
		
		String access_token = WeixinUtil.getAccessToken(Sign.appid, Sign.appsecret);
		
	
		/*Map<String,Object> map = new HashMap<String, Object>();
		map.put("expire_seconds", 604800);
		map.put("action_name", "QR_LIMIT_SCENE");
		Map<String,Map<String,Integer>> scene = new HashMap<String, Map<String,Integer>>();
		Map<String,Integer> scene_id = new HashMap<String, Integer>();
		scene_id.put("scene_id", id);
		scene.put("scene", scene_id);
		map.put("action_info", scene);*/
		
		String outputStr = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\":{\"scene\": {\"scene_str\":%s}}}";
		
		//String data = new Gson().toJson(map);
		JsonObject object = WeixinUtil.httpRequest(URL+access_token, "POST",String.format(outputStr, id+""));
		if(object != null){
			try {
				String ticket = URLEncoder.encode(object.get("ticket").getAsString(),"UTF-8");
				//JsonObject json = WeixinUtil.httpRequest(ticket_url+ticket, "GET", null);
				//System.out.println(json);
				return ticket_url+ticket;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				String errCode = object.get("errcode").getAsString();
				return errCode;
			}
		}
		return "ticket为空";
		
		
	}
	
}

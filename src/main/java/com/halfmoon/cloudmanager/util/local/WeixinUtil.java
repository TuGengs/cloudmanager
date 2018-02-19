
package com.halfmoon.cloudmanager.util.local;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class WeixinUtil {
	
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"; 
	public final static String ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	
	public static JsonObject httpRequest(String requestUrl,String requestMethod,String outputStr){
		
		JsonObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			
			TrustManager[] tm = {new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();    
            httpUrlConn.setSSLSocketFactory(ssf);    
    
            httpUrlConn.setDoOutput(true);    
            httpUrlConn.setDoInput(true);    
            httpUrlConn.setUseCaches(false);    
            // 设置请求方式（GET/POST）    
            httpUrlConn.setRequestMethod(requestMethod);    
    
            if ("GET".equalsIgnoreCase(requestMethod))    
                httpUrlConn.connect();    
    
            // 当有数据需要提交时    
            if (null != outputStr) {    
                OutputStream outputStream = httpUrlConn.getOutputStream();    
                // 注意编码格式，防止中文乱码    
                outputStream.write(outputStr.getBytes("UTF-8"));    
                outputStream.close();    
            } 
         // 将返回的输入流转换成字符串    
            InputStream inputStream = httpUrlConn.getInputStream();    
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
    
            String str = null;    
            while ((str = bufferedReader.readLine()) != null) {    
                buffer.append(str);
            } 
            
            bufferedReader.close();    
            inputStreamReader.close();    
            // 释放资源    
            inputStream.close();    
            inputStream = null;    
            httpUrlConn.disconnect();
           	JsonParser parser = new JsonParser();
            jsonObject = parser.parse(buffer.toString()).getAsJsonObject();
            
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		return jsonObject;
	}
	public static Map<String,String> accessCache = new HashMap<String, String>();
	
	public static String getAccessToken(String appid, String appsecret) {    
		
		String time = accessCache.get("time");
		Long nowDate = new Date().getTime();
		
		if(time != null){
			if(nowDate - Long.parseLong(time) < 7200*1000){
				return accessCache.get("accessToken");
			}else{
				String request_url = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
                JsonObject jsonObject = httpRequest(request_url, "GET", null);  
                String access_token = jsonObject.get("access_token").getAsString();
                accessCache.remove("accessToken"); 
                accessCache.remove("time");
                accessCache.put("time", new Date().getTime()+""); 
                accessCache.put("accessToken", access_token);
                return access_token;
			}
		}else{
			String request_url = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
            JsonObject jsonObject = httpRequest(request_url, "GET", null);  
            String access_token = jsonObject.get("access_token").getAsString();
            accessCache.remove("accessToken"); 
            accessCache.remove("time");
            accessCache.put("time", new Date().getTime()+""); 
            accessCache.put("accessToken", access_token);
            return access_token;
		}
	    
	            
        /*accessToken.setToken(jsonObject.get("access_token").getAsString());    
        accessToken.setExpiresIn(jsonObject.get("expires_in").getAsInt()); */   
	        
	}  
	
	public static Map<String,String> weixinCache = new HashMap<String, String>();
	
	public static String getJsApiTicket(String accessToken) {   
		String time = weixinCache.get("time");  
		Long nowDate = new Date().getTime();
		
		if(time != null){
			if(nowDate - Long.parseLong(time) < 7200*1000){
				return weixinCache.get("ticket");
			}else{
				String request_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi".replace("ACCESS_TOKEN", accessToken);  
                JsonObject jsonObject = httpRequest(request_url, "GET", null);  
                String ticket = jsonObject.get("ticket").getAsString();  
                weixinCache.remove("ticket"); 
                weixinCache.remove("time");
                weixinCache.put("time", new Date().getTime()+""); 
                weixinCache.put("ticket", ticket);
                return ticket;
			}
			
		}else{
			String request_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi".replace("ACCESS_TOKEN", accessToken);  
            JsonObject jsonObject = httpRequest(request_url, "GET", null);  
            String ticket = jsonObject.get("ticket").getAsString();  
            weixinCache.remove("ticket"); 
            weixinCache.remove("time");
            weixinCache.put("time", new Date().getTime()+""); 
            weixinCache.put("ticket", ticket);
            return ticket;
		}
         
    } 
	
}


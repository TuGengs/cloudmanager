package com.halfmoon.cloudmanager.util.upload;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.halfmoon.cloudmanager.util.local.Sign;
import com.halfmoon.cloudmanager.util.local.WeixinUtil;

public class FaceUtil {
	
	public static String URL = "http://file.api.weixin.qq.com/cgi-bin/media"
			+ "/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	
	
	public static byte[] getFaceByte(int meida_id){
		
		String access_token = WeixinUtil.getAccessToken(Sign.appid, Sign.appsecret);
		URL = URL.replace("ACCESS_TOKEN", access_token);
		URL = URL.replace("MEDIA_ID", meida_id+"");
		
		return getImageFromUrl(URL);
	}
	public static byte[] getImageFromUrl(String strUrl){
		try {    
            URL url = new URL(strUrl);    
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
            conn.setRequestMethod("GET");    
            conn.setConnectTimeout(5 * 1000);    
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据    
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据    
            return btImg;    
        } catch (Exception e) {    
            e.printStackTrace();    
        }
		return null;
	}
	public static byte[] readInputStream(InputStream inStream) throws Exception{    
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        while( (len=inStream.read(buffer)) != -1 ){    
            outStream.write(buffer, 0, len);    
        }    
        inStream.close();    
        return outStream.toByteArray();    
    }
}

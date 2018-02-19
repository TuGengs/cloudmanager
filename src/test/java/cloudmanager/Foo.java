package cloudmanager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Foo {

	public static void main(String[] args) {
		
		String authorizeUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		authorizeUrl = authorizeUrl
				.replace("APPID", "wx21687581623d15a6")
				.replace("SECRET", "0d88bbeaeb38ef7e11fe4a7f98b1cd5c")
				.replace("CODE", "123");
		
		System.out.println(authorizeUrl);
		
	}
	
}

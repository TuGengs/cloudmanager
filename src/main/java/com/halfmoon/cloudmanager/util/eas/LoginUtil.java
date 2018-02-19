package com.halfmoon.cloudmanager.util.eas;

import java.util.HashMap;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class LoginUtil {
	
	public boolean login(String sysnum, String password) throws Exception {
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("UserName", sysnum);
		data.put("PassWord", password);
		
		Response rsp = Jsoup.connect("http://jwgl.jxau.edu.cn/User/CheckLogin")
							 .method(Connection.Method.POST)
							 .data(data)
							 .timeout(5000)
							 .execute();
		
		return rsp.url().getPath().split("/")[1].equals("Main");
		
	}

}

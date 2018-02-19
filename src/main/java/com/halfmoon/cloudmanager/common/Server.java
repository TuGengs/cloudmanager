package com.halfmoon.cloudmanager.common;

public class Server {
	
	// 顶级域名
	public static final String TOP_LEVEL_DOMAIN = "gxykq.com";
	
	// 获取主页url
	public static String getHomepageUrl() {
		
		return "http://www." + TOP_LEVEL_DOMAIN;
		
	}
	
}

package com.halfmoon.cloudmanager.util.authcode;

import java.util.Date;

/**
 * 保存验证码的相关信息，存放在session中
 * 包括验证码内容、生成时间、是否已验证成功
 * @author hzq
 *
 */
public class AuthcodeInfo {
	
	public String code = null;
	public Date createTime = null;
	public boolean verified = false;
	
	public AuthcodeInfo(String code) {
		
		this.code = code;
		this.createTime = new Date();
		
	}
	
	public void verified() {
		
		this.verified = true;
		
	}
	
	public boolean isExpired(long duration) {

		return ((new Date().getTime()) - this.createTime.getTime()) / 1000 > duration;
		
	}

}

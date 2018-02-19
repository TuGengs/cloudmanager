package com.halfmoon.cloudmanager.util.authcode;

import java.util.Random;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SmsAuthCode {

	private String url;
	private String appkey;
	private String secret;
	private String signName;
	private String templateCode;
	
	// 验证码
	private String code;
	
	// 验证码位数
	public static int DIGIT;
	public static long EXPIRE_TIME;
	
	/**
	 * 发送包含验证码的短信
	 * @param tel 电话号码
	 * @return 短信是否成功发送
	 */
	public boolean sentCode(String tel) {
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		code = generateCode();
		
		req.setSmsType("normal");
		req.setSmsFreeSignName(signName);
		req.setSmsParamString("{\"code\":\"" + code + "\"}");
		req.setRecNum(tel);
		req.setSmsTemplateCode(templateCode);
		
		try {
			AlibabaAliqinFcSmsNumSendResponse resp = client.execute(req);
			if(!resp.isSuccess()) {
				System.out.println(resp.getErrorCode() + resp.getMsg());
				return false;
			}		
		} catch (ApiException e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println(code);
		
		return true;
	}
	
	/**
	 * 随机生成短信验证码
	 * @return 短信验证码
	 */
	public String generateCode() {
		
		Random random = new Random();
		char candidate[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		StringBuffer code = new StringBuffer("");
		
		for(int i = 0; i < DIGIT; i++) {
			code.append(candidate[random.nextInt(candidate.length)]);
		}
		
		return code.toString();
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public void setDIGIT(int dIGIT) {
		DIGIT = dIGIT;
	}
	
	public void setEXPIRE_TIME(long eXPIRE_TIME) {
		EXPIRE_TIME = eXPIRE_TIME;
	}

}

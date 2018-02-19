package com.halfmoon.cloudmanager.util.authcode;

import java.awt.image.BufferedImage;
import java.util.Properties;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

/**
 * 图形验证码工具类
 * @author hzq
 *
 */
public class PictureAuthcode {

	private static Properties props = new Properties();
	private static Producer kaptchaProducer = null;

	private String code = null;
	private BufferedImage picture = null;
	
	// 图片格式
	public static String FORMAT;
	// 过期时间
	public static long EXPIRE_TIME;
	
	// 设置验证码属性
	static {

		props.put("kaptcha.border", "no");
		props.put("kaptcha.textproducer.font.color", "black");
		props.put("kaptcha.textproducer.char.space", "5");
		props.put("kaptcha.background.clear.from", "red");
		props.put("kaptcha.background.clear.to", "blue");
		props.put("kaptcha.textproducer.font.color", "white");

		Config config = new Config(props);
		kaptchaProducer = config.getProducerImpl();
	}

	/**
	 * 创建新验证码
	 */
	public void create() {

		this.code = kaptchaProducer.createText();
		this.picture = kaptchaProducer.createImage(code);

	}

	public void setFORMAT(String fORMAT) {
		FORMAT = fORMAT;
	}

	public void setEXPIRE_TIME(long eXPIRE_TIME) {
		EXPIRE_TIME = eXPIRE_TIME;
	}

	public String getCode() {
		return code;
	}

	public BufferedImage getPicture() {
		return picture;
	}

}

package com.halfmoon.cloudmanager.controller.api.auth.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.halfmoon.cloudmanager.controller.api.auth.IAuthController;
import com.halfmoon.cloudmanager.response.OperationResult;
import com.halfmoon.cloudmanager.service.user.impl.UserService;
import com.halfmoon.cloudmanager.util.authcode.AuthcodeInfo;
import com.halfmoon.cloudmanager.util.authcode.PictureAuthcode;
import com.halfmoon.cloudmanager.util.authcode.SmsAuthCode;

@Controller
@RequestMapping("auth")
public class AuthController implements IAuthController {
	
	@Resource
	PictureAuthcode pictureAuthcode;
	
	@Resource
	SmsAuthCode smsAuthcode;
	
	@Autowired
	UserService userService;
	
	@Override
	@RequestMapping(path = "/captcha/pic")
	public void getPictureAuthcode(HttpSession session, HttpServletResponse response) {
		
		pictureAuthcode.create();
		session.setAttribute("pictureAuthcode",
				new AuthcodeInfo(pictureAuthcode.getCode()));
		BufferedImage picture = pictureAuthcode.getPicture();
		
		// 设置返回格式
		response.setContentType("image/" + PictureAuthcode.FORMAT);
		try {
			OutputStream os = response.getOutputStream();
			ImageIO.setUseCache(false);
			ImageIO.write(picture, PictureAuthcode.FORMAT, os);
		} catch (IOException e) {
			// 一般情况下不会出错，所以就不处理了
			e.printStackTrace();
		}
	}

	@Override
	@ResponseBody
	@RequestMapping(path = "/captcha/pic/verify", method = RequestMethod.POST)
	public OperationResult validatePictureAuthCode(HttpSession session, String userInput) {
		
		AuthcodeInfo pictureAuthCode = (AuthcodeInfo) session.getAttribute("pictureAuthcode");
		
		if(!pictureAuthCode.code.equals(userInput))
			return new OperationResult("验证码输入错误！");
		else if(pictureAuthCode.isExpired(PictureAuthcode.EXPIRE_TIME))
			return new OperationResult("验证码已失效，请重新输入！");
		else {
			pictureAuthCode.verified();
			return new OperationResult();
		}
		
	}

	@Override
	@ResponseBody
	@RequestMapping(path = "/captcha/sms", method = RequestMethod.POST)
	public OperationResult getSMSAuthcode(HttpSession session, String tel) {
		System.out.println(123123);
		
		if(!smsAuthcode.sentCode(tel)) {
			return new OperationResult("短信发送失败！");
		}
		
		session.setAttribute("smsAuthcode", new AuthcodeInfo(smsAuthcode.getCode()));
		
		return new OperationResult();
		
	}

	@Override
	@ResponseBody
	@RequestMapping(path = "/captcha/sms/verify", method = RequestMethod.POST)
	public OperationResult validateSMSAuthcode(HttpSession session, String userInput) {
		
		AuthcodeInfo smsAuthcode = (AuthcodeInfo) session.getAttribute("smsAuthcode");
		
		if(!smsAuthcode.code.equals(userInput))
			return new OperationResult("验证码输入错误！");
		else if(smsAuthcode.isExpired(SmsAuthCode.EXPIRE_TIME))
			return new OperationResult("验证码已失效，请重新输入！");
		else {
			smsAuthcode.verified();
			return new OperationResult();
		}
		
	}
	
}

package com.halfmoon.cloudmanager.interceptor.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.halfmoon.cloudmanager.util.authcode.AuthcodeInfo;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("success", "0");

		return true;
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		HttpSession session = request.getSession();
		
		String success = (String) session.getAttribute("success");
		if(success == null || success.equals("0") ) {
			return;
		}

		AuthcodeInfo pictureAuthcode = (AuthcodeInfo) session.getAttribute("pictureAuthcode");
		AuthcodeInfo smsAuthcode = (AuthcodeInfo) session.getAttribute("smsAuthcode");

		if (pictureAuthcode != null) {
			if (pictureAuthcode.verified) {
				session.removeAttribute("pictureAuthcode");
			}
		}

		if (smsAuthcode != null) {
			if (smsAuthcode.verified) {
				session.removeAttribute("smsAuthcode");
			}
		}

	}

}

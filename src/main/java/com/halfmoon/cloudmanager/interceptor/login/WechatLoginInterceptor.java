package com.halfmoon.cloudmanager.interceptor.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.service.user.impl.UserService;

public class WechatLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	UserService userService;
		
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		
		LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
		String openid = (String) session.getAttribute("openid");
		
		if(openid == null || loggedUser == null) {
			return;
		}
		
		userService.bindOpenid(loggedUser.getId(), openid);
		session.removeAttribute("openid");
		
	}

}

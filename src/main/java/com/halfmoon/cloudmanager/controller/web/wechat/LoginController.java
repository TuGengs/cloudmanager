package com.halfmoon.cloudmanager.controller.web.wechat;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.service.user.impl.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;

	@RequestMapping(path = "/login/wechat")
	public void loginWithOpenId(HttpServletRequest req, HttpServletResponse rsp,
			HttpSession session) throws IOException {
		
		req.setCharacterEncoding("UTF-8");
        rsp.setCharacterEncoding("UTF-8");

		String code = req.getParameter("code");
		String state = req.getParameter("state");

		if (code == null || !state.equals("jxau")) {
			rsp.sendRedirect("/login");
			return;
		}

		// 微信授权url
		String authorizeUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		authorizeUrl = authorizeUrl
						.replace("APPID", "wx21687581623d15a6")
						.replace("SECRET", "0d88bbeaeb38ef7e11fe4a7f98b1cd5c")
						.replace("CODE", code);
		
		Response jsonRsp = Jsoup.connect(authorizeUrl)
					 			.method(Connection.Method.POST)
					 			.execute();
		
		JsonObject data = new JsonParser().parse(jsonRsp.body()).getAsJsonObject();
		JsonElement jsonElement = data.get("openid");
		if(jsonElement == null) {
			rsp.sendRedirect("/login");
			return;
		}
		String openid = jsonElement.getAsString();
		
		LoggedUser loggedUser = userService.loginThroughWechat(openid);
		if(loggedUser == null) {
			session.setAttribute("openid", openid);
			rsp.sendRedirect("/login");
			return;
		}
		
		session.setAttribute("loggedUser", loggedUser);
		rsp.sendRedirect("/check");

	}

}

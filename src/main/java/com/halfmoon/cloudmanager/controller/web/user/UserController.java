package com.halfmoon.cloudmanager.controller.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("webUser")
public class UserController {
	
	@RequestMapping(path = "/register/common")
	public String registerCommon() {
		return "register1";
	}
	
	@RequestMapping(path = "/register/schoolInfo")
	public String registerSchoolInfo() {
		return "register2";
	}
	
	@RequestMapping(path = "/register/extInfo")
	public String registerExtInfo() {
		return "register3";
	}
	
	@RequestMapping(path = "/register/easLogin")
	public String registerEasLogin() {
		return "register4";
	}
	
	@RequestMapping(path = "/profile")
	public String getProfile() {
		return "set-info";
	}
	
	@RequestMapping(path = "/login") 
	public String login() {
		return "login";
	}
	
	@RequestMapping(path = "/resetPassword")
	public String resetPassword() {
		return "forget-password";
	}
	
}

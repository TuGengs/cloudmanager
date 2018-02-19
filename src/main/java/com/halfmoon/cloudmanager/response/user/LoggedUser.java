package com.halfmoon.cloudmanager.response.user;

/**
 * 
 * 登录验证成功后，将用户信息放入此类中
 * @author hzq
 *
 */
public class LoggedUser {

	private int id;
	private String name;
	private String school;
	private String icon_url;	// 头像的URL

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	
}

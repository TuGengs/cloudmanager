package com.halfmoon.cloudmanager.response.user;

import com.halfmoon.cloudmanager.model.school.School;
import com.halfmoon.cloudmanager.model.user.User;

/**
 * 用于存放详细列表中的用户信息
 * 
 * @author hzq
 *
 */
public class UserInfo extends User {

	private School school;
	private String icon_url;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

}

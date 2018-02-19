package com.halfmoon.cloudmanager.response.check;

import com.google.gson.JsonArray;
import com.halfmoon.cloudmanager.response.user.UserInfo;

public class GradeRecord {

	private int id;
	private UserInfo user;
	private JsonArray grading_items;
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public JsonArray getGrading_items() {
		return grading_items;
	}

	public void setGrading_items(JsonArray grading_items) {
		this.grading_items = grading_items;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

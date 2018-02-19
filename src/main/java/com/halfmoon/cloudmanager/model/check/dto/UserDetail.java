package com.halfmoon.cloudmanager.model.check.dto;

public class UserDetail {
	
	private String class_name;
	private int gender;
	private String tel;
	private String remark;
	private String img_url;
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	@Override
	public String toString() {
		return "UserDetail [class_name=" + class_name + ", gender=" + gender
				+ ", tel=" + tel + ", remark=" + remark + ", img_url="
				+ img_url + "]";
	}
	
	
	
	
}

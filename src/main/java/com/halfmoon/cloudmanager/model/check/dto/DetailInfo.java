package com.halfmoon.cloudmanager.model.check.dto;

public class DetailInfo {
	private int user_id;
	private String name;
	private String icon_url;
	private String class_name;
	private int is_here;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public int getIs_here() {
		return is_here;
	}
	public void setIs_here(int is_here) {
		this.is_here = is_here;
	}
	@Override
	public String toString() {
		return "DetailInfo [user_id=" + user_id + ", name=" + name
				+ ", icon_url=" + icon_url + ", class_name=" + class_name
				+ ", is_here=" + is_here + "]";
	}
	
	
	
}

package com.halfmoon.cloudmanager.model.check.dto;
/**
 * 学生信息 detail界面
 * @author Administrator
 *
 */
public class UInfo {
	private int user_id;
	private int sysnum;
	private String name;
	private String icon_url;
	private String class_name;
	private int lack_times; // 缺到次数
	
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
	
	public int getLack_times() {
		return lack_times;
	}
	public void setLack_times(int lack_times) {
		this.lack_times = lack_times;
	}
	
	public int getSysnum() {
		return sysnum;
	}
	public void setSysnum(int sysnum) {
		this.sysnum = sysnum;
	}
	@Override
	public String toString() {
		return "UInfo [user_id=" + user_id + ", sysnum=" + sysnum + ", name="
				+ name + ", icon_url=" + icon_url + ", class_name="
				+ class_name + ", lack_times=" + lack_times + "]";
	}
	
	
}

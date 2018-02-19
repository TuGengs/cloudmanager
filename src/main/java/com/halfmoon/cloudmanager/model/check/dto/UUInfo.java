package com.halfmoon.cloudmanager.model.check.dto;

public class UUInfo {
	
	private int user_id;
	private int sysnum;
	private String name;
	private String class_name;
	private int lack_times;
	
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
		return "UUInfo [user_id=" + user_id + ", sysnum=" + sysnum + ", name="
				+ name + ", class_name=" + class_name + ", lack_times="
				+ lack_times + "]";
	}

	
	
	
}

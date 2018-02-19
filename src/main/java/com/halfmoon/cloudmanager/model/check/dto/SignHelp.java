package com.halfmoon.cloudmanager.model.check.dto;

public class SignHelp {
	
	private int check_auto_id;
	private int creator_id;
	private String sub_name;
	
	public int getCheck_auto_id() {
		return check_auto_id;
	}
	public void setCheck_auto_id(int check_auto_id) {
		this.check_auto_id = check_auto_id;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	@Override
	public String toString() {
		return "SignHelp [check_auto_id=" + check_auto_id + ", creator_id="
				+ creator_id + ", sub_name=" + sub_name + "]";
	}
	
	
	
}

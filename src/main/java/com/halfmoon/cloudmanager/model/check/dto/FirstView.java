package com.halfmoon.cloudmanager.model.check.dto;
/**
 * 第一个检查界面
 * @author Administrator
 *
 */
public class FirstView {
	
	private int check_id;
	private String check_name;
	private String second_name;
	public int getCheck_id() {
		return check_id;
	}
	public void setCheck_id(int check_id) {
		this.check_id = check_id;
	}
	public String getCheck_name() {
		return check_name;
	}
	public void setCheck_name(String check_name) {
		this.check_name = check_name;
	}
	public String getSecond_name() {
		return second_name;
	}
	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}
	@Override
	public String toString() {
		return "FirstView [check_id=" + check_id + ", check_name=" + check_name
				+ ", second_name=" + second_name + "]";
	}
	
	
	
}

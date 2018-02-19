package com.halfmoon.cloudmanager.model.check;
/**
 * 检查和学生对应表
 * @author xiaogao.XU
 *
 */
public class ListCheck {
	
	private int check_id;
	private int user_id;
	private int auto_id;
	private int check_type;
	
	public ListCheck() {
		super();
	}
	
	public ListCheck(int sign_check_id, int user_id, int sign_check_auto_id,int check_type) {
		this.check_id = sign_check_id;
		this.user_id = user_id;
		this.auto_id = sign_check_auto_id;
		this.check_type = check_type;
	}
	
	public ListCheck(int sign_check_id, int user_id) {
		this.check_id = sign_check_id;
		this.user_id = user_id;
	}

	
	public int getCheck_id() {
		return check_id;
	}

	public void setCheck_id(int check_id) {
		this.check_id = check_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAuto_id() {
		return auto_id;
	}

	public void setAuto_id(int auto_id) {
		this.auto_id = auto_id;
	}

	public int getCheck_type() {
		return check_type;
	}

	public void setCheck_type(int check_type) {
		this.check_type = check_type;
	}
	
	
	
}

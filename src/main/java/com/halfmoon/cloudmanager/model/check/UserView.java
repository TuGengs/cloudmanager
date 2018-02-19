package com.halfmoon.cloudmanager.model.check;
/**
 * 用户检查查看表实体类
 * @author xiaogao.XU
 *
 */
public class UserView {
	
	private int id;
	private int creator_id;
	private int sign_in_check_id;
	private int check_type;
	
	
	public UserView(int creator_id, int sign_in_check_id,int check_type) {
		this.creator_id = creator_id;
		this.sign_in_check_id = sign_in_check_id;
		this.check_type = check_type;
	}
	public UserView(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSign_in_check_id() {
		return sign_in_check_id;
	}
	public void setSign_in_check_id(int sign_in_check_id) {
		this.sign_in_check_id = sign_in_check_id;
	}
	public int getCheck_type() {
		return check_type;
	}
	public void setCheck_type(int check_type) {
		this.check_type = check_type;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	
	
}

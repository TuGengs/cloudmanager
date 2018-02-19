package com.halfmoon.cloudmanager.model.check;

public class DetailedSignInCheck {

	private int id;
	private int single_sign_check_auto_id;
	private int user_id;
	private String user_name;
	private int is_here;
	private int signed_times;
	private int sign_type;
	private int manager_id;
	private int ought_sign_times;
	private String icon_url;
	private int sign_check_id;
	private String remark;
	
	public DetailedSignInCheck() {
		super();
	}
	public DetailedSignInCheck(int single_sign_check_auto_id, int user_id,
			String user_name, int is_here, String icon_url,int sign_check_id) {
		super();
		this.single_sign_check_auto_id = single_sign_check_auto_id;
		this.user_name = user_name;
		this.user_id = user_id;
		this.is_here = is_here;
		this.icon_url = icon_url;
		this.sign_check_id = sign_check_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSingle_sign_check_auto_id() {
		return single_sign_check_auto_id;
	}
	public void setSingle_sign_check_auto_id(int single_sign_check_auto_id) {
		this.single_sign_check_auto_id = single_sign_check_auto_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getIs_here() {
		return is_here;
	}
	public void setIs_here(int is_here) {
		this.is_here = is_here;
	}
	public int getSigned_times() {
		return signed_times;
	}
	public void setSigned_times(int signed_times) {
		this.signed_times = signed_times;
	}
	public int getSign_type() {
		return sign_type;
	}
	public void setSign_type(int sign_type) {
		this.sign_type = sign_type;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public int getOught_sign_times() {
		return ought_sign_times;
	}
	public void setOught_sign_times(int ought_sign_times) {
		this.ought_sign_times = ought_sign_times;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public int getSign_check_id() {
		return sign_check_id;
	}
	public void setSign_check_id(int sign_check_id) {
		this.sign_check_id = sign_check_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String tip) {
		this.remark = tip;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	

}

package com.halfmoon.cloudmanager.model.check;

import java.util.Date;

public class SingleSignInCheck {

	private int id;
	private int sign_in_check_id;
	private Date create_time;
	private String remark;			// 备注

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}

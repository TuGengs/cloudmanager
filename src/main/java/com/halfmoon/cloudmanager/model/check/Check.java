package com.halfmoon.cloudmanager.model.check;

import java.util.Date;

public class Check {

	private int id;
	private int check_id;
	private String name;
	private transient int creator_id;
	private Date create_time;
	private byte is_allowed;
	private Date start_time;
	private int cycle;			// 0表示周期是一个月，默认-1（无）
	private byte checker_type;
	private String remark;		// 说明
	private String sub_name;

	public Check(String check_name, String second_name, int user_id,
			Date date, int creator, int allow) {
		this.name = check_name;
		this.sub_name = second_name;
		this.creator_id = user_id;
		this.create_time = date;
		this.checker_type = (byte) creator;
		this.is_allowed = (byte) allow;
	}
	public Check(){}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCheck_id() {
		return check_id;
	}

	public void setCheck_id(int check_id) {
		this.check_id = check_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public byte getIs_allowed() {
		return is_allowed;
	}

	public void setIs_allowed(byte is_allowed) {
		this.is_allowed = is_allowed;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public String getRemark() {
		return remark;
	}

	public byte getChecker_type() {
		return checker_type;
	}

	public void setChecker_type(byte checker_type) {
		this.checker_type = checker_type;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

}

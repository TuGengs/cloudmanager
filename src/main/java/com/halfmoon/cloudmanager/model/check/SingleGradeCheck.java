package com.halfmoon.cloudmanager.model.check;

import java.util.Date;

/**
 * @author LITAO
 * @time 上午10:03:46  2017年3月11日
 * @info 
 * @修改 新添了remark，grade_check_id修改为grading_check_id
 */
public class SingleGradeCheck {

	private int id;
	private int grading_check_id;
	private Date create_time;
	private String remark;		// 备注

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrading_check_id() {
		return grading_check_id;
	}

	public void setGrading_check_id(int grading_check_id) {
		this.grading_check_id = grading_check_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
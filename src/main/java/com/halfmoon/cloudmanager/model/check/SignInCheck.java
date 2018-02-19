package com.halfmoon.cloudmanager.model.check;

import java.util.Date;

public class SignInCheck extends Check {

	private float score_absence; 	// 每次缺勤扣的分
	private float score_late;		// 每次迟到扣的分（实际上签得不全的都是）
	private float score_leave; 		// 每次请假扣的分

	public SignInCheck(String check_name, String second_name, int user_id, Date date, int creator, int allow) {
		super(check_name, second_name, user_id, date, creator, allow);
	}

	public SignInCheck() {
	}

	public float getScore_absence() {

		return score_absence;
	}

	public void setScore_absence(float score_absence) {
		this.score_absence = score_absence;
	}

	public float getScore_late() {
		return score_late;
	}

	public void setScore_late(float score_late) {
		this.score_late = score_late;
	}

	public float getScore_leave() {
		return score_leave;
	}

	public void setScore_leave(float score_leave) {
		this.score_leave = score_leave;
	}

	//
	private Date end_time;
	private Date class_time;
	private String location;
	private String rule;
	private Date cut_off_time;

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Date getClass_time() {
		return class_time;
	}

	public void setClass_time(Date class_time) {
		this.class_time = class_time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Date getCut_off_time() {
		return cut_off_time;
	}

	public void setCut_off_time(Date cut_off_time) {
		this.cut_off_time = cut_off_time;
	}

}

package com.halfmoon.cloudmanager.model.check;

/**
 * @author LITAO
 * @time 上午9:54:52  2017年3月11日
 * @info 
 * @修改 新添加了remark，grades被修改为score
 */
public class GradingItem {

	private int id;
	private int grade_check_id;
	private String name;
	private float score;
	/**
	 * 这一项，之前我的没有，新添的
	 */
	private String remark; // 说明

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrade_check_id() {
		return grade_check_id;
	}

	public void setGrade_check_id(int grade_check_id) {
		this.grade_check_id = grade_check_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

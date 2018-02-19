package com.halfmoon.cloudmanager.model.check;

import com.google.gson.JsonArray;

/**
 * @author LITAO
 * @time 上午10:13:06  2017年3月11日
 * @info 详细检查信息
 * @修改
 */
public class DetailedGradeCheck {

	private int id;
	/**
	 * 这个是前一张表的自增id
	 */
	private int single_grade_check_id;
	/**
	 *  强调是user的自增id，不是学号
	 */
	private transient int user_id;
	/**
	 * 强调是user的自增id
	 */
	private transient int manager_id;
	private JsonArray grading_items;

	/**
	 * 胡中清的model中没有这一项
	 * 放这儿，是将一个人所有的扣分项扣的总分汇总
	 */
	private float single_grade;
	public float getSingle_grade() {
		return single_grade;
	}
	public void setSingle_grade(float single_grade) {
		this.single_grade = single_grade;
	}

	/**
	 * @author LITAO
	 * @time 上午10:12:50  2017年3月11日
	 * @info 胡中清新添的内部类，规范上面的grading_items，即单个人被扣分的扣分项集合
	 * @修改
	 */
	protected class InnerGradingItems {

		protected int id;
		protected String name;
		protected float score;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSingle_grade_check_id() {
		return single_grade_check_id;
	}

	public void setSingle_grade_check_id(int single_grade_check_id) {
		this.single_grade_check_id = single_grade_check_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public JsonArray getGrading_items() {
		return grading_items;
	}

	public void setGrading_items(JsonArray grading_items) {
		this.grading_items = grading_items;
	}
	
	public InnerGradingItems getInnerGradingItems() {
		return new InnerGradingItems();
	}

}
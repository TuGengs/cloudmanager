package com.halfmoon.cloudmanager.response.gradecheck;

/**
 * 在解析json数组的时候，会用到这个类去接受被扣的分数
 * @author mushi
 *
 */
public class Item {
	float grade;

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}
	
}

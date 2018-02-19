package com.halfmoon.cloudmanager.response.gradecheck;

import com.google.gson.JsonArray;

/**
 * 这个用来接受前端反馈给后台的，每个人扣分的情况
 * 
 * @author mushi
 *
 */
public class PeopleDetailedGrades {
	private int id;
	private JsonArray grades;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public JsonArray getGrades() {
		return grades;
	}
	public void setGrades(JsonArray grades) {
		this.grades = grades;
	}
	
}

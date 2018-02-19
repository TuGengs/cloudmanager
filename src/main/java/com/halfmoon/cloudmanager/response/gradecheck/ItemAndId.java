package com.halfmoon.cloudmanager.response.gradecheck;

import com.google.gson.JsonArray;

/**
 * 在调用updateItems的时候，用这个类型接受数据
 * @author mushi
 *
 */
public class ItemAndId {
	private JsonArray grading_items;
	private int id;
	public JsonArray getGrading_items() {
		return grading_items;
	}
	public void setGrading_items(JsonArray grading_items) {
		this.grading_items = grading_items;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}

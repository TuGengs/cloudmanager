package com.halfmoon.cloudmanager.model.check.dto;

public class LackTimes {
	
	private int user_id;
	private int times;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "LackTimes [user_id=" + user_id + ", times=" + times + "]";
	}
	
	
}

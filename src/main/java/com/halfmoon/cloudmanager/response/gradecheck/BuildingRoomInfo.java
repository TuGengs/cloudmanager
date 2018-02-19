package com.halfmoon.cloudmanager.response.gradecheck;

/**
 * @author LITAO
 * @time 下午5:09:28  2017年3月11日
 * @info 这个类主要就是用来向数据库中传值取值，操作的是user表
 * @修改
 */
public class BuildingRoomInfo {
	private int id;
	private String name;
	private String building_name;
	private String room_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	
}

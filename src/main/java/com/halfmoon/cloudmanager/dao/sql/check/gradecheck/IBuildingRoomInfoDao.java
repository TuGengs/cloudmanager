package com.halfmoon.cloudmanager.dao.sql.check.gradecheck;

import java.util.ArrayList;

import com.halfmoon.cloudmanager.response.gradecheck.BuildingRoomInfo;

public interface IBuildingRoomInfoDao {
	
	/**
	 * 通过传过来的id集合，查询到不重复的building_name
	 * @param idList
	 * @return
	 */
	public ArrayList<String> getBuilding(ArrayList<Integer> idList);
	/**
	 * 通过building得到room
	 * @param building
	 * @return
	 */
	public ArrayList<String> getRoom(String building);
	/**
	 * 将返回得到的结果都组成json
	 * @param idList
	 * @return
	 */
	public ArrayList<BuildingRoomInfo> getInfo(String building_name, String room_name);
	
}

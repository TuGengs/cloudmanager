package com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.IBuildingRoomInfoDao;
import com.halfmoon.cloudmanager.response.gradecheck.BuildingRoomInfo;

@Repository
public class BuildingRoomInfoDao extends BaseDao<BuildingRoomInfo> implements IBuildingRoomInfoDao{

	SqlSession sqlSession;

	@Override
	public ArrayList<String> getBuilding(ArrayList<Integer> idList) {
		// TODO Auto-generated method stub
		sqlSession = this.getSqlSession();
		List<String> result = new ArrayList<String>();
		result = sqlSession.selectList(this.NAMESPACE.concat("getBuilding"), idList);
		return (ArrayList<String>)result;
	}

	@Override
	public ArrayList<String> getRoom(String building) {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<String>();
		sqlSession = this.getSqlSession();
		result = sqlSession.selectList(this.NAMESPACE.concat("getRoom"), building);
		return (ArrayList<String>)result;
	}

	@Override
	public ArrayList<BuildingRoomInfo> getInfo(String building_name, String room_name) {
		// TODO Auto-generated method stub
		List<BuildingRoomInfo> result = new ArrayList<BuildingRoomInfo>();
		sqlSession = this.getSqlSession();
		BuildingRoomInfo buildingRoomInfo = new BuildingRoomInfo();
		buildingRoomInfo.setBuilding_name(building_name);
		buildingRoomInfo.setRoom_name(room_name);
		result = sqlSession.selectList(this.NAMESPACE.concat("getInfo"), buildingRoomInfo);
		return (ArrayList<BuildingRoomInfo>)result;
	}


}

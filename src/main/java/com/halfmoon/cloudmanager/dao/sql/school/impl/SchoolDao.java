package com.halfmoon.cloudmanager.dao.sql.school.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.school.ISchoolDao;
import com.halfmoon.cloudmanager.model.school.School;

@Repository
public class SchoolDao extends BaseDao<School> implements ISchoolDao {

	@Override
	public List<School> getSchoolsNearby(double longitude, double latitude) {
		
		SqlSession sqlSession = this.getSqlSession();
		
		HashMap<String, Double> parameterMap = new HashMap<String, Double>();
		parameterMap.put("longitude", longitude);
		parameterMap.put("latitude", latitude);
		
		List<School> schoolsNearby = sqlSession.selectList(
				this.NAMESPACE.concat("getSchoolsNearby"), parameterMap);
		
		return schoolsNearby;
	}

	@Override
	public List<School> getSchoolsByKeyword(String keyword) {
		
		SqlSession sqlSession = this.getSqlSession();

		List<School> schools = sqlSession.selectList(
				this.NAMESPACE.concat("getSchoolsByKeyword"), keyword);
		
		return schools;
		
	}

}

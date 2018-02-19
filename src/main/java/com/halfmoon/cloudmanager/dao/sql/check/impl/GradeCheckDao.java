package com.halfmoon.cloudmanager.dao.sql.check.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.ICheckDao;
import com.halfmoon.cloudmanager.model.check.GradeCheck;
import com.halfmoon.cloudmanager.model.check.SingleGradeCheck;
import com.halfmoon.cloudmanager.response.check.GradeRecord;

@Repository
public class GradeCheckDao extends BaseDao<GradeCheck> implements ICheckDao {

	@Override
	public List<GradeRecord> getAllDetailsOfSingleCheck(int single_grade_check_id) {

		SqlSession sqlSession = this.getSqlSession();
		
		List<GradeRecord> gradeRecord = sqlSession.selectList(
				this.NAMESPACE.concat("getAllDetailsOfSingleCheck"), single_grade_check_id);
		
		return gradeRecord;
		
	}
	
	@Override
	public List<SingleGradeCheck> getSingleChecksByCycle(int grade_check_id, int cycle_time) {
		
		SqlSession sqlSession = this.getSqlSession();
		
		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("grade_check_id", grade_check_id);
		parameterMap.put("cycle_time", cycle_time);
		
		List<SingleGradeCheck> SingleChecksInCycle = sqlSession.selectList(
				this.NAMESPACE.concat("getSingleChecksByCycle"),
				parameterMap);
		
		return SingleChecksInCycle;
		
	}
	
	@Override
	public List<HashMap<String, Object>> getRawResultData(int grade_check_id) {
		
		SqlSession sqlSession = this.getSqlSession();
		
		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("grade_check_id", grade_check_id);
		
		List<HashMap<String, Object>> rawGradeData = sqlSession.selectList(
				this.NAMESPACE.concat("getRawResultData"), parameterMap);
		
		return rawGradeData;
	}
	
	@Override
	public List<HashMap<String, Object>> getRawResultData(int user_id, int grade_check_id) {
		
		SqlSession sqlSession = this.getSqlSession();
		
		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("user_id", user_id);
		parameterMap.put("grade_check_id", grade_check_id);

		List<HashMap<String, Object>> rawGradeData = sqlSession.selectList(
				this.NAMESPACE.concat("getRawResultData"), parameterMap);
		
		return rawGradeData;
	}

}

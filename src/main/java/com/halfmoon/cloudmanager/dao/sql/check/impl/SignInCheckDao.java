package com.halfmoon.cloudmanager.dao.sql.check.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.ICheckDao;
import com.halfmoon.cloudmanager.model.check.SignInCheck;
import com.halfmoon.cloudmanager.model.check.SingleSignInCheck;
import com.halfmoon.cloudmanager.response.check.SignInRecord;

@Repository
public class SignInCheckDao extends BaseDao<SignInCheck> implements ICheckDao {

	@Override
	public List<SignInRecord> getAllDetailsOfSingleCheck(
			int single_sign_in_check_id) {
		
		SqlSession sqlSession = this.getSqlSession();
		
		List<SignInRecord> AllDetailsOfSingleCheck = sqlSession.selectList(
				this.NAMESPACE.concat("getAllDetailsOfSingleCheck"),
				single_sign_in_check_id);
		
		return AllDetailsOfSingleCheck;
		
	}
	
	@Override
	public List<SingleSignInCheck> getSingleChecksByCycle(
			int sign_in_check_id, int cycle_time) {

		SqlSession sqlSession = this.getSqlSession();
		
		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("sign_in_check_id", sign_in_check_id);
		parameterMap.put("cycle_time", cycle_time);
		
		List<SingleSignInCheck> SingleChecksInCycle = sqlSession.selectList(
				this.NAMESPACE.concat("getSingleChecksByCycle"),
				parameterMap);
		
		return SingleChecksInCycle;
		
	}

	@Override
	public List<HashMap<String, Object>> getRawResultData(int sign_in_check_id) {
		
		SqlSession sqlSession = this.getSqlSession();
		
		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("sign_in_check_id", sign_in_check_id);
		List<HashMap<String, Object>> rawSignInData = sqlSession.selectList(
				this.NAMESPACE.concat("getRawResultData"), parameterMap);
		
		return rawSignInData;
		
	}
	
	@Override
	public List<HashMap<String, Object>> getRawResultData(int user_id, int sign_in_check_id) {
		
		SqlSession sqlSession = this.getSqlSession();
		
		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("sign_in_check_id", sign_in_check_id);
		parameterMap.put("user_id", user_id);
		
		List<HashMap<String, Object>> rawSignInData = sqlSession.selectList(
				this.NAMESPACE.concat("getRawResultData"), parameterMap);
		
		return rawSignInData;
		
	}

}

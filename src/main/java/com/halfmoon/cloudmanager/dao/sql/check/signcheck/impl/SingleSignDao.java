package com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.ISingleSignDao;
import com.halfmoon.cloudmanager.model.check.SingleSignInCheck;

@Repository
public class SingleSignDao extends BaseDao<SingleSignInCheck> implements ISingleSignDao{
	
	SqlSession sqlSession;


	@Override
	public List<SingleSignInCheck> getAll(int check_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sign_in_check_id", check_id);
		
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getAll"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getCheck_id(int single_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("single_id", single_id);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getCheck_id"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public List<Integer> getSingleId(int check_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getSingleId"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean deleteByCheckId(int check_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		
		try {
			sqlSession.delete(this.NAMESPACE.concat("deleteByCheckId"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean updateRemark(int single_id, String content) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("single_id", single_id);
		map.put("content", content);
		try {
			sqlSession.update(this.NAMESPACE.concat("updateRemark"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public String getRemark(int single_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("single_id", single_id);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getRemark"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	

}

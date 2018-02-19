package com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.IUserViewDao;
import com.halfmoon.cloudmanager.model.check.UserView;
import com.halfmoon.cloudmanager.model.check.dto.FirstView;
import com.halfmoon.cloudmanager.model.check.dto.UUInfo;

@Repository
public class UserViewDao extends BaseDao<UserView> implements IUserViewDao{
	
	SqlSession sqlSession;


	@Override
	public List<UUInfo> getViewerList(int check_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("type", type);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getViewerList"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteViewer(int check_id, int user_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("type", type);
		map.put("user_id", user_id);
		try {
			sqlSession.delete(this.NAMESPACE.concat("deleteViewer"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public boolean deleteByCheckId(int check_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("type", type);
		try {
			sqlSession.delete(this.NAMESPACE.concat("deleteByCheckId"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean deleteByAutoIdAndUserId(int auto_id, int user_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("auto_id", auto_id);
		map.put("user_id", user_id);
		map.put("type", type);
		try {
			sqlSession.delete(this.NAMESPACE.concat("deleteByAutoIdAndUserId"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<FirstView> getAllCheckView(int user_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("type", type);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getAllCheckView"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int isExist(int user_id, int check_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("type", type);
		map.put("check_id", check_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("isExist"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	
	
	

	
	
}

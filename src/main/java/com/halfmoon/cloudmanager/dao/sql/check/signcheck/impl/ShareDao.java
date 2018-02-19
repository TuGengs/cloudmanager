package com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.IShareDao;
import com.halfmoon.cloudmanager.model.check.Share;
import com.halfmoon.cloudmanager.model.check.dto.ShareInfo;

@Repository
public class ShareDao extends BaseDao<Share> implements IShareDao{
	
	SqlSession sqlSession;

	
	@Override
	public Share getByAccount(String account) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("account", account);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getByAccount"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ShareInfo getInfo(int check_id, int type) {
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
			return sqlSession.selectOne(this.NAMESPACE.concat("getInfo"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updatePwd(int check_id, String pwd, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("type", type);
		map.put("pwd", pwd);
		System.out.println(check_id+pwd+" type:"+type);
		try {
			sqlSession.update(this.NAMESPACE.concat("updatePwd"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateStatus(int check_id, int is_open, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("type", type);
		map.put("is_open", is_open);
		try {
			sqlSession.update(this.NAMESPACE.concat("updateStatus"), map);
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
	
	

}

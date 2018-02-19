package com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.ISignCheckDao;
import com.halfmoon.cloudmanager.model.check.SignInCheck;
import com.halfmoon.cloudmanager.model.check.dto.FirstView;
import com.halfmoon.cloudmanager.model.check.dto.SignHelp;

@Repository
public class SignCheckDao extends BaseDao<SignInCheck> implements ISignCheckDao{
	
	SqlSession sqlSession;

	@Override
	public boolean updateCheckId(int checkId, int id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", checkId);
		map.put("id", id);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("updateCheckId"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public int getAutoId(int check_id, int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("user_id", user_id);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getAutoId"), map);
		}catch (NullPointerException e){
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}	
	}

	@Override
	public List<FirstView> getAllByUserId(int userId) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("creator_id", userId);
		
			return sqlSession.selectList(this.NAMESPACE.concat("getAllByUserId"), map);
		
	}


	@Override
	public int getCheckIDByCode(String code) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("code", code);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getCheckIDByCode"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String getNameByID(int check_auto_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_auto_id", check_auto_id);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getNameByID"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SignHelp> getSignHelps(int check_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getSignHelps"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getUserId(int check_auto_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_auto_id", check_auto_id);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getUserId"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public FirstView getDName(int check_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getDName"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public boolean updateCheckName(int check_id, String check_name) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("check_name", check_name);
		try {
			sqlSession.update(this.NAMESPACE.concat("updateCheckName"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateSubName(int check_id, String sub_name) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("sub_name", sub_name);
		try {
			sqlSession.update(this.NAMESPACE.concat("updateSubName"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	public boolean updateCycle(Map<String, Object> map) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			sqlSession.update(this.NAMESPACE.concat("updateCycle"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
}

package com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.IListCheckDao;
import com.halfmoon.cloudmanager.model.check.ListCheck;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.check.dto.UUInfo;

@Repository
public class ListCheckDao extends BaseDao<ListCheck> implements IListCheckDao{
	
	SqlSession sqlSession;

	@Override
	public boolean updateAutoId(int check_id, int check_auto_id, int type,
			List<Integer> list) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		map.put("check_auto_id", check_auto_id);
		map.put("type", type);
		map.put("list", list);
		try {
			sqlSession.update(this.NAMESPACE.concat("updateAutoId"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<String> getAvilList(int check_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		map.put("type", type);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getAvilList"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UInfo> getAllIds(int check_id,int auto_id,int type,int single_id,int order) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		if(auto_id != -1){
			map.put("auto_id", auto_id);
		}
		if(single_id != -1){
			map.put("single_id", single_id);
		}
		if(order != 0){
			map.put("order", order);
		}
		map.put("type", type);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getAllIds"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<UUInfo> getByAutoId(int check_id, int check_auto_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		map.put("type", type);
		map.put("check_auto_id", check_auto_id);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getByAutoId"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ListCheck> getChecksBySys(int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getChecksBySys"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteUserList(int check_id, List<Integer> array, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		map.put("array", array);
		map.put("type", type);
		try {
			sqlSession.delete(this.NAMESPACE.concat("deleteUserList"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public int getCount(int check_id, int check_auto_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		map.put("check_auto_id", check_auto_id);
		map.put("type", type);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getCount"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<ListCheck> getSignInfo(int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getSignInfo"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int isExists(ListCheck listCheck) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("isExists"), listCheck);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
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
		Map<String,Object> map = new HashMap<String,Object>();
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
	public boolean updateAutoIdForCheckId(int check_id, int auto_id, int type) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		map.put("auto_id", auto_id);
		map.put("type", type);
		try {
			sqlSession.delete(this.NAMESPACE.concat("updateAutoIdForCheckId"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<UUInfo> getUserByView(int check_id, int sort_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id",check_id);
		map.put("sort_id", sort_id);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getUserByView"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<UUInfo> getList(int auto_id,int check_id,int type){
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		if(auto_id != -1){
			map.put("auto_id", auto_id);
		}
		
		
		map.put("type", type);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getList"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

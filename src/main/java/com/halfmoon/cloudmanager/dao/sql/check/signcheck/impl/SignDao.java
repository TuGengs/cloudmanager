package com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.ISignDao;
import com.halfmoon.cloudmanager.model.check.Sign;

@Repository
public class SignDao extends BaseDao<Sign> implements ISignDao{
	
	SqlSession sqlSession;

	@Override
	public Sign get(int single_check_auto_id, int check_auto_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("single_id", single_check_auto_id);
		map.put("check_id", check_auto_id);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("get"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean closeSelf(int check_id, int check_auto_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_auto_id", check_auto_id);
		map.put("check_id", check_id);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("closeSelf"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean closeAll(int check_id, int single_check_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("single_auto_id", single_check_id);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("closeAll"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public boolean closeSelfOnce(int single_check_id, int check_auto_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_auto_id", check_auto_id);
		map.put("single_check_id", single_check_id);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("closeSelfOnce"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
		
	}

	@Override
	public int getStatus(int single_check_id, int check_auto_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("single_check_id", single_check_id);
		map.put("check_auto_id", check_auto_id);
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getStatus"), map);
		}catch(NullPointerException e){
			return 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Sign getByBossOpen(int check_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getByBossOpen"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Sign getByHelperOpen(int check_id, int check_auto_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("check_auto_id", check_auto_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getByHelperOpen"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getIs_open(int check_id, int check_auto_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_id", check_id);
		map.put("check_auto_id", check_auto_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getIs_open"), map);
		}catch (NullPointerException e){
			return 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean updateAddress(int check_auto_id, int single_id,
			double latitude, double longitude) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check_auto_id", check_auto_id);
		map.put("single_id", single_id);
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		try {
			sqlSession.update(this.NAMESPACE.concat("updateAddress"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Sign getSignBySingleIdAndOpen(int single_id, int check_auto_id,int check_id,int is_open) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		if(single_id != -1){
			map.put("single_id", single_id);
		}
		
		if(check_auto_id != -1){
			map.put("auto_id", check_auto_id);	
		}
		if(check_id != -1){
			map.put("check_id", check_id);
		}
		if(is_open != -1){
			map.put("is_open", is_open);
		}
		
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getSignBySingleIdAndOpen"), map);
		}catch (NullPointerException e){
			return null;
		}
		catch (Exception e) {
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
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean deleteByAutoId(int auto_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("auto_id", auto_id);
		try {
			sqlSession.delete(this.NAMESPACE.concat("deleteByAutoId"), map);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}

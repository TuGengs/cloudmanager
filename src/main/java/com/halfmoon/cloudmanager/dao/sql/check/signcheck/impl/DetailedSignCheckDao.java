package com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.IDetailedSignCheckDao;
import com.halfmoon.cloudmanager.model.check.DetailedSignInCheck;
import com.halfmoon.cloudmanager.model.check.dto.DetailInfo;
import com.halfmoon.cloudmanager.model.check.dto.LackTimes;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.check.dto.UserDetail;

@Repository
public class DetailedSignCheckDao extends BaseDao<DetailedSignInCheck> implements IDetailedSignCheckDao{

	SqlSession sqlSession;
		
	@Override
	public boolean updateOughtTimes(int single_check_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_check_id", single_check_id);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("updateOughtTimes"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int is_Here(int single_id, int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_id", single_id);
		map.put("user_id", user_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("is_Here"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int getLackTimes(int check_id, int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		map.put("user_id", user_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getLackTimes"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int getOughtTimes(int single_id, int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_id", single_id);
		map.put("user_id", user_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getOughtTimes"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean updateStatus(int single_id, int user_id, int ought,
			int status, String tip) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_check_id", single_id);
		map.put("user_id", user_id);
		map.put("ought", ought);
		map.put("status", status);
		map.put("tip", tip);
		try {
			sqlSession.update(this.NAMESPACE.concat("updateStatus"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateStatusWithoutOught(int single_id, int user_id,
			int status, String tip) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_check_id", single_id);
		map.put("user_id", user_id);
		map.put("status", status);
		map.put("tip", tip);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("updateStatusWithoutOught"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean handleSign(int single_id, int user_id,int status) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_id", single_id);
		map.put("user_id", user_id);
		map.put("status", status);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("handleSign"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean saveList(List<DetailedSignInCheck> list) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		try {
			sqlSession.insert(this.NAMESPACE.concat("saveList"), list);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int getSingleLackTimes(int single_id, int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_id", single_id);
		map.put("user_id", user_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getSingleLackTimes"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
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
		
		Map<String,Object> map = new HashMap<String,Object>();
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
	public List<LackTimes> getUserIdSortByLackTimes(int check_id,
			List<UInfo> list) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		map.put("list", list);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getUserIdSortByLackTimes"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<DetailInfo> test(int check_id, int type) {
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
			return sqlSession.selectList(this.NAMESPACE.concat("test"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<Integer> getSingleIdByUserIdAndCheckId(int user_id, int check_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("check_id", check_id);
		map.put("user_id", user_id);
		return sqlSession.selectList(this.NAMESPACE.concat("getSingleIdByUserIdAndCheckId"), map);
	}
	@Override
	public boolean deleteBySingleId(int single_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_id", single_id);
		sqlSession.delete(this.NAMESPACE.concat("deleteBySingleId"), map);
		return true;
		
	}
	@Override
	public boolean saveSingleRemark(int single_id, int user_id, String remark) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_id", single_id);
		map.put("user_id", user_id);
		map.put("remark", remark);
		
		try {
			sqlSession.update(this.NAMESPACE.concat("saveSingleRemark"), map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public UserDetail getUserDetail(int user_id, int single_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("single_id", single_id);
		map.put("user_id", user_id);
		return sqlSession.selectOne(this.NAMESPACE.concat("getUserDetail"), map);
		
	}
	
	
}
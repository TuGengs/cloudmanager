package com.halfmoon.cloudmanager.dao.sql.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.halfmoon.cloudmanager.dao.sql.BaseDao;
import com.halfmoon.cloudmanager.dao.sql.user.IUserDao;
import com.halfmoon.cloudmanager.dao.sql.user.icon.impl.IconDao;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.user.User;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.response.user.UserInfo;

/**
 * 封装了用户相关的数据库操作
 * 
 * @author hzq
 *
 */
@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

	@Autowired
	IconDao iconDao;

	SqlSession sqlSession;

	@Override
	public boolean registerWithData(User user) {

		sqlSession = this.getSqlSession();

		int success = sqlSession.insert(this.NAMESPACE.concat("registerWithData"), user);

		return success > 0;

	}

	@Override
	public LoggedUser validate(String tel, String hashed_password) {

		sqlSession = this.getSqlSession();

		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("tel", tel);
		parameterMap.put("hashed_password", hashed_password);
		LoggedUser loggedUser = sqlSession.selectOne(
				this.NAMESPACE.concat("getLoggedUser"), parameterMap);

		return loggedUser;

	}
	
	public LoggedUser getLoggedUserByOpenid(String openid) {
		
		sqlSession = this.getSqlSession();
		
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("openid", openid);
		LoggedUser loggedUser = sqlSession.selectOne(
				this.NAMESPACE.concat("getLoggedUser"), parameterMap);
		
		return loggedUser;

	}
	
	public boolean relateWithOpenid(int id, String openid) {
		
		sqlSession = this.getSqlSession();
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", id);
		parameterMap.put("openid", openid);
		int success = sqlSession.update(
				this.NAMESPACE.concat("relateWithOpenid"), parameterMap);
		
		return success > 0;

	}
	
	public boolean removeOpenid(int id) {
		
		sqlSession = this.getSqlSession();
		
		int success = sqlSession.update(
				this.NAMESPACE.concat("removeOpenid"), id);
		
		return success > 0;
		
	}
	
	// 用户修改信息后更新session中的用户信息
	public LoggedUser refresh(int id) {

		sqlSession = this.getSqlSession();

		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("id", id);
		LoggedUser loggedUser = sqlSession.selectOne(
				this.NAMESPACE.concat("getLoggedUser"), parameterMap);

		return loggedUser;

	}

	@Override
	public boolean resetPassword(String tel, String new_hashed_password) {

		sqlSession = this.getSqlSession();

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("tel", tel);
		parameterMap.put("hashed_password", new_hashed_password);
		int success = sqlSession.update(
				this.NAMESPACE.concat("resetPassword"), parameterMap);

		return success > 0;

	}

	@Override
	public boolean relateWithIcon(int id, int icon_id) {

		sqlSession = this.getSqlSession();

		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("id", id);
		parameterMap.put("icon_id", icon_id);

		int success = sqlSession.update(this.NAMESPACE.concat("relateWithIcon"), parameterMap);

		return success > 0;

	}

	public boolean relateWithPhoto(int id, int photo_id) {

		sqlSession = this.getSqlSession();

		Map<String, Integer> parameterMap = new HashMap<String, Integer>();
		parameterMap.put("id", id);
		parameterMap.put("photo_id", photo_id);

		int success = sqlSession.update(
				this.NAMESPACE.concat("relateWithPhoto"), parameterMap);

		return success > 0;

	}

	@Override
	public UserInfo getUserInfo(int id) {

		sqlSession = this.getSqlSession();

		UserInfo userInfo = sqlSession.selectOne(
				this.NAMESPACE.concat("getUserInfo"), id);

		return userInfo;

	}

	public boolean checkoutIfTelHadBeenUsed(String tel) {

		sqlSession = this.getSqlSession();

		return sqlSession.selectOne(
				this.NAMESPACE.concat("checkoutIfTelHadBeenUsed"), tel) != null;

	}

	@Override
	public UInfo getUserDList(int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			return null;
		}
		User user = new User();
		user.setId(user_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getUserDList"), user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public UInfo getUserIcon(int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			return null;
		}
		User user = new User();
		user.setId(user_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getUserIcon"), user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	@Override
	public List<String> getUserName(List<Integer> list) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			return null;
		}

		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		map.put("list", list);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getUserName"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UInfo getUserInfoByTel(String tel) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			return null;
		}
		User user = new User();
		user.setTel(tel);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getUserInfoByTel"), user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getName(int user_id) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		try {
			return sqlSession.selectOne(this.NAMESPACE.concat("getName"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UInfo> getUserInfoByStatus(int single_id, List<Integer> list) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("single_id", single_id);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getUserInfoByStatus"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UInfo> getUserInfoBySys(int single_id, List<Integer> list) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("single_id", single_id);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getUserInfoBySys"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UInfo> getUserInfoByName(int single_id, List<Integer> list) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("single_id", single_id);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getUserInfoByName"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UInfo> getUserInfoWithoutSort(List<Integer> list) {
		try {
			sqlSession = this.getSqlSession();
		} catch (Exception e) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		try {
			return sqlSession.selectList(this.NAMESPACE.concat("getUserInfoWithoutSort"), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

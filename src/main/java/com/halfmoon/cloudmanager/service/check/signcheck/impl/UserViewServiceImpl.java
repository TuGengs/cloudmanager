package com.halfmoon.cloudmanager.service.check.signcheck.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.UserViewDao;
import com.halfmoon.cloudmanager.model.check.Share;
import com.halfmoon.cloudmanager.model.check.UserView;
import com.halfmoon.cloudmanager.model.check.dto.FirstView;
import com.halfmoon.cloudmanager.model.check.dto.UUInfo;
import com.halfmoon.cloudmanager.service.check.signcheck.ShareService;
import com.halfmoon.cloudmanager.service.check.signcheck.UserViewService;
/**
 * 
 * @author xiaogao.XU
 *
 */
@Service
public class UserViewServiceImpl implements UserViewService{
	@Autowired
	private UserViewDao userViewDao;
	@Autowired
	private ShareService shareService;
	
	@Override
	public void save(UserView userView) {
		userViewDao.add(userView);
	}

	@Override
	public void update(UserView userView) {
		userViewDao.modify(userView);
	}

	@Override
	public void delete(int id) {
		userViewDao.delete(id);
	}

	@Override
	public UserView get(int id) {
		return (UserView) userViewDao.get(id);
	}

	@Override
	public List<UUInfo> getViewerList(int check_id, int type) {
		return userViewDao.getViewerList(check_id, type);
	}

	@Override
	public boolean deleteViewer(int check_id, int sysnum, int type) {
		return userViewDao.deleteViewer(check_id, sysnum, type);
	}
	@Override
	public boolean deleteByCheckId(int check_id,int type){
		return userViewDao.deleteByCheckId(check_id,type);
	}

	@Override
	public boolean delete(int auto_id, int user_id, int type) {
		return userViewDao.deleteByAutoIdAndUserId(auto_id,user_id,type);
	}

	@Override
	public List<FirstView> getCheckView(int user_id, int type) {
		return userViewDao.getAllCheckView(user_id, type);
	}

	@Override
	public boolean isExist(int user_id, int check_id, int type) {
		if(userViewDao.isExist(user_id, check_id, type) > 0){
			return true;
		}
		return false;
	}

	

}

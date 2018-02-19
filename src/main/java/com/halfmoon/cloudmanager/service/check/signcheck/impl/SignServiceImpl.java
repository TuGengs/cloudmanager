package com.halfmoon.cloudmanager.service.check.signcheck.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.SignDao;
import com.halfmoon.cloudmanager.model.check.Sign;
import com.halfmoon.cloudmanager.service.check.signcheck.SignService;
/**
 * 
 * @author xiaogao.XU
 *
 */
@Service
public class SignServiceImpl implements SignService{

	@Autowired
	private SignDao signDao;
	
	@Override
	public void save(Sign sign) {
		signDao.add(sign);
	}

	@Override
	public void closeSelf(int check_id, int check_auto_id) {
		signDao.closeSelf(check_id, check_auto_id);
	}

	@Override
	public Sign get(int single_auto_id, int check_auto_id) {
		return signDao.get(single_auto_id, check_auto_id);
	}

	@Override
	public void update(Sign sign) {
		signDao.modify(sign);
	}

	@Override
	public boolean isOpen(int single_check_id, int check_auto_id) {
		int status = signDao.getStatus(single_check_id, check_auto_id);
		
		if(status == 1){
			return true;
		}
		return false;
	}

	@Override
	public void closeAll(int check_id, int single_check_id) {
		signDao.closeAll(check_id, single_check_id);
	}

	@Override
	public void closeSelfOnce(int single_check_id, int check_auto_id) {
		signDao.closeSelfOnce(single_check_id, check_auto_id);
	}

	@Override
	public Sign getByBossOpen(int check_id) {
		return signDao.getByBossOpen(check_id);
	}

	@Override
	public Sign getByHelperOpen(int check_id, int check_auto_id) {
		return signDao.getByHelperOpen(check_id, check_auto_id);
	}

	@Override
	public JsonObject getCheckList(String sysnum) {
		
		return null;
	}

	@Override
	public int getStatus(int check_id, int check_auto_id) {
		return signDao.getIs_open(check_id,check_auto_id);
	}

	@Override
	public boolean updateAddress(int check_auto_id, int single_id,
			double latitude, double longitude) {
		return signDao.updateAddress(check_auto_id, single_id, latitude, longitude);
	}

	@Override
	public Sign getSignBySingleIdAndOpen(int single_id,int check_auto_id,
			int check_id,int is_open) {
		return signDao.getSignBySingleIdAndOpen(single_id,check_auto_id,check_id,is_open);
	}
	@Override
	public boolean deleteByCheckId(int check_id){
		return signDao.deleteByCheckId(check_id);
	}

	@Override
	public boolean deleteByAutoId(int auto_id) {
		return signDao.deleteByAutoId(auto_id);
	}
	
	
	
}

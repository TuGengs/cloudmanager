package com.halfmoon.cloudmanager.service.check.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halfmoon.cloudmanager.dao.sql.check.impl.SignInCheckDao;
import com.halfmoon.cloudmanager.model.check.SingleSignInCheck;
import com.halfmoon.cloudmanager.response.check.RawSignInDataAndInfo;
import com.halfmoon.cloudmanager.response.check.SignInCheckInfo;
import com.halfmoon.cloudmanager.response.check.SignInRecord;
import com.halfmoon.cloudmanager.service.check.CheckService;

@Service
public class SignInCheckService extends CheckService<RawSignInDataAndInfo> {

	@Autowired
	private SignInCheckDao signInCheckDao;

	@Override
	public List<SignInRecord> getAllDetailsOfSingleCheck(int single_sign_in_check_id) {
		
		return signInCheckDao.getAllDetailsOfSingleCheck(single_sign_in_check_id);
	
	}

	@Override
	public List<SingleSignInCheck> getSingleChecksByCycle(int sign_in_check_id, int cycle_time) {
		
		return signInCheckDao.getSingleChecksByCycle(sign_in_check_id, cycle_time);
	
	}
	
	@Override
	public RawSignInDataAndInfo getRawResultDataAndInfo(int sign_in_check_id) {
		
		RawSignInDataAndInfo result = new RawSignInDataAndInfo();
		
		result.setSignInCheckInfo((SignInCheckInfo) signInCheckDao.get(sign_in_check_id));
		result.setTotalResult((List<HashMap<String, Object>>) signInCheckDao.getRawResultData(sign_in_check_id));
		
		return result;
		
	}
	
	@Override
	public RawSignInDataAndInfo getRawResultDataAndInfo(int user_id, int sign_in_check_id) {
		
		RawSignInDataAndInfo result = new RawSignInDataAndInfo();
		
		result.setSignInCheckInfo((SignInCheckInfo) signInCheckDao.get(sign_in_check_id));
		result.setTotalResult((List<HashMap<String, Object>>) signInCheckDao.getRawResultData(user_id, sign_in_check_id));
		
		return result;
		
	}
	
}

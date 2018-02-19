package com.halfmoon.cloudmanager.service.check.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halfmoon.cloudmanager.dao.sql.check.impl.GradeCheckDao;
import com.halfmoon.cloudmanager.model.check.SingleGradeCheck;
import com.halfmoon.cloudmanager.response.check.GradeCheckInfo;
import com.halfmoon.cloudmanager.response.check.GradeRecord;
import com.halfmoon.cloudmanager.response.check.RawGradeDataAndInfo;
import com.halfmoon.cloudmanager.service.check.CheckService;

@Service
public class GradeCheckService extends CheckService<RawGradeDataAndInfo> {

	@Autowired
	private GradeCheckDao gradeCheckDao;

	@Override
	public List<GradeRecord> getAllDetailsOfSingleCheck(int single_grade_check_id) {

		return gradeCheckDao.getAllDetailsOfSingleCheck(single_grade_check_id);

	}
	
	@Override
	public List<SingleGradeCheck> getSingleChecksByCycle(int grade_check_id, int cycle_time) {
		
		return gradeCheckDao.getSingleChecksByCycle(grade_check_id, cycle_time);
		
	}

	@Override
	public RawGradeDataAndInfo getRawResultDataAndInfo(int grade_check_id) {

		RawGradeDataAndInfo result = new RawGradeDataAndInfo();

		result.setGradeCheckInfo((GradeCheckInfo) gradeCheckDao.get(grade_check_id));
		result.setTotalResult(gradeCheckDao.getRawResultData(grade_check_id));

		return result;

	}
	
	@Override
	public RawGradeDataAndInfo getRawResultDataAndInfo(int user_id, int grade_check_id) {

		RawGradeDataAndInfo result = new RawGradeDataAndInfo();

		result.setGradeCheckInfo((GradeCheckInfo) gradeCheckDao.get(grade_check_id));
		result.setTotalResult(gradeCheckDao.getRawResultData(user_id, grade_check_id));

		return result;

	}

}

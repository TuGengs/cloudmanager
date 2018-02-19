package com.halfmoon.cloudmanager.service.school.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halfmoon.cloudmanager.dao.sql.school.impl.SchoolDao;
import com.halfmoon.cloudmanager.model.school.School;
import com.halfmoon.cloudmanager.service.school.ISchoolService;

@Service
public class SchoolService implements ISchoolService {
	
	@Autowired
	SchoolDao schoolDao;

	@Override
	public List<School> getSchoolsNearby(double longitude, double latitude) {
		
		return schoolDao.getSchoolsNearby(longitude, latitude);
		
	}

	@Override
	public List<School> getSchoolsByKeyword(String keyword) {
		
		return schoolDao.getSchoolsByKeyword(keyword);
		
	}

}

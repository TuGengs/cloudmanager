package com.halfmoon.cloudmanager.service.school;

import java.util.List;

import com.halfmoon.cloudmanager.model.school.School;

public interface ISchoolService {
	
	public List<School> getSchoolsNearby(double longitude, double latitude);
	
	public List<School> getSchoolsByKeyword(String keyword);

}

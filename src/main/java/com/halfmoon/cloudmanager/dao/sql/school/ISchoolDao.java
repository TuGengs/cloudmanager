package com.halfmoon.cloudmanager.dao.sql.school;

import java.util.List;

import com.halfmoon.cloudmanager.model.school.School;

public interface ISchoolDao {
	
	public List<School> getSchoolsNearby(double longitude, double latitude);
	
	public List<School> getSchoolsByKeyword(String keyword);

}

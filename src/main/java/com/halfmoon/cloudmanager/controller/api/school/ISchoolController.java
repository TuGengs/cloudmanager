package com.halfmoon.cloudmanager.controller.api.school;

import com.halfmoon.cloudmanager.response.OperationResultWithData;

public interface ISchoolController {
	
	public OperationResultWithData<?>
			getSchoolsNearby(double longitude, double latitude);
	
	public OperationResultWithData<?>
			getSchoolsByKeyword(String keyword);
	
}

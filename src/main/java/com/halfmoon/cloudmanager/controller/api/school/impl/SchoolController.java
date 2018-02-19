package com.halfmoon.cloudmanager.controller.api.school.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.halfmoon.cloudmanager.controller.api.school.ISchoolController;
import com.halfmoon.cloudmanager.model.school.School;
import com.halfmoon.cloudmanager.response.OperationResultWithData;
import com.halfmoon.cloudmanager.service.school.impl.SchoolService;

@Controller
@RequestMapping("resource/school")
public class SchoolController implements ISchoolController {
	
	@Autowired
	SchoolService schoolService;
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/nearby", method = RequestMethod.POST)
	public OperationResultWithData<List<School>>
			getSchoolsNearby(double longitude, double latitude) {
		
		OperationResultWithData<List<School>> result = new OperationResultWithData<List<School>>();
		
		List<School> schoolsNearby = schoolService.getSchoolsNearby(longitude, latitude);
		
		result.setData(schoolsNearby);
		
		return result;
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/keyword", method = RequestMethod.POST)
	public OperationResultWithData<List<School>>
			getSchoolsByKeyword(String keyword) {
		
		OperationResultWithData<List<School>> result = new OperationResultWithData<List<School>>();
		
		List<School> schools = schoolService.getSchoolsByKeyword(keyword);
		
		result.setData(schools);
		
		return result;
		
	}

}

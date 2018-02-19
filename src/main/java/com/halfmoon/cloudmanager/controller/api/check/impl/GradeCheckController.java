package com.halfmoon.cloudmanager.controller.api.check.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.halfmoon.cloudmanager.controller.api.check.ICheckController;
import com.halfmoon.cloudmanager.model.check.GradeCheck;
import com.halfmoon.cloudmanager.model.check.SingleGradeCheck;
import com.halfmoon.cloudmanager.response.OperationResultWithData;
import com.halfmoon.cloudmanager.response.check.GradeRecord;
import com.halfmoon.cloudmanager.response.check.RawGradeDataAndInfo;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.service.check.impl.GradeCheckService;

@Controller
@RequestMapping("check/grade")
public class GradeCheckController implements ICheckController {
	
	@Autowired
	GradeCheckService gradeCheckService;
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/mkcode/{grade_check_id}")
	public OperationResultWithData<String>
			generateCode(@PathVariable("grade_check_id") int grade_check_id) {
		
		OperationResultWithData<String> result = new OperationResultWithData<String>();
		
		result.setData(gradeCheckService.generateCheckCode(grade_check_id, GradeCheck.class));
		
		return result;
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/record/{single_grade_check_id}")
	public OperationResultWithData<List<GradeRecord>>
			getSingleCheckRecord(@PathVariable("single_grade_check_id") int single_grade_check_id) {
		
		OperationResultWithData<List<GradeRecord>> result = new OperationResultWithData<List<GradeRecord>>();
		
		result.setData(gradeCheckService.getAllDetailsOfSingleCheck(single_grade_check_id));
		
		return result;
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/single/{grade_check_id}/cycle/{cycle_time}")
	public OperationResultWithData<List<SingleGradeCheck>>
			getSingleChecksByCycle(
					@PathVariable("grade_check_id") int grade_check_id,
					@PathVariable("cycle_time") int cycle_time) {

		OperationResultWithData<List<SingleGradeCheck>> result = new OperationResultWithData<List<SingleGradeCheck>>();
		
		result.setData(gradeCheckService.getSingleChecksByCycle(grade_check_id, cycle_time));
		
		return result;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/total/{grade_check_id}")
	public OperationResultWithData<RawGradeDataAndInfo>
			getRawDataOfTotalResult(@PathVariable("grade_check_id") int grade_check_id) {
		
		OperationResultWithData<RawGradeDataAndInfo> result = new OperationResultWithData<RawGradeDataAndInfo>();
		
		result.setData(gradeCheckService.getRawResultDataAndInfo(grade_check_id));
		
		return result;
		
	}

	@Override
	@ResponseBody
	@RequestMapping(path = "/total/{grade_check_id}/user/{user_id}")
	public OperationResultWithData<RawGradeDataAndInfo>
			getRawDataOfTotalResult(
					@PathVariable("user_id") int user_id,
					@PathVariable("grade_check_id") int grade_check_id) {
		
		OperationResultWithData<RawGradeDataAndInfo> result = new OperationResultWithData<RawGradeDataAndInfo>();
		
		result.setData(gradeCheckService.getRawResultDataAndInfo(user_id, grade_check_id));
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(path = "/total/{grade_check_id}/self")
	public OperationResultWithData<RawGradeDataAndInfo>
			getSelfRawDataOfTotalResult(
					@PathVariable("grade_check_id") int grade_check_id,
					HttpSession session) {
		
		int user_id = ((LoggedUser) session.getAttribute("loggedUser")).getId();
		
		return getRawDataOfTotalResult(user_id, grade_check_id);
		
	}

}

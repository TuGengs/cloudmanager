package com.halfmoon.cloudmanager.controller.api.check.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.halfmoon.cloudmanager.controller.api.check.ICheckController;
import com.halfmoon.cloudmanager.model.check.SignInCheck;
import com.halfmoon.cloudmanager.model.check.SingleSignInCheck;
import com.halfmoon.cloudmanager.response.OperationResultWithData;
import com.halfmoon.cloudmanager.response.check.RawSignInDataAndInfo;
import com.halfmoon.cloudmanager.response.check.SignInRecord;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.service.check.impl.SignInCheckService;

@Controller
@RequestMapping("check/signin")
public class SignInCheckController implements ICheckController {

	@Autowired
	SignInCheckService signInCheckService;
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/mkcode/{sign_in_check_id}")
	public OperationResultWithData<String>
			generateCode(@PathVariable("sign_in_check_id") int sign_in_check_id) {
		OperationResultWithData<String> result = new OperationResultWithData<String>();
		
		result.setData(signInCheckService.generateCheckCode(sign_in_check_id, SignInCheck.class));
		
		return result;
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/record/{single_sign_in_check_id}")
	public OperationResultWithData<List<SignInRecord>>
			getSingleCheckRecord(@PathVariable("single_sign_in_check_id") int single_sign_in_check_id) {
		
		OperationResultWithData<List<SignInRecord>> detailedSignInCheckResult = new OperationResultWithData<List<SignInRecord>>();
		
		detailedSignInCheckResult.setData(signInCheckService.getAllDetailsOfSingleCheck(single_sign_in_check_id));
		
		return detailedSignInCheckResult;
		
	}

	@Override
	@ResponseBody
	@RequestMapping(path = "/single/{sign_in_check_id}/cycle/{cycle_time}")
	public OperationResultWithData<List<SingleSignInCheck>>
			getSingleChecksByCycle(
					@PathVariable("sign_in_check_id") int sign_in_check_id,
					@PathVariable("cycle_time") int cycle_time) {
		
		OperationResultWithData<List<SingleSignInCheck>> singleSignInCheckResult = new OperationResultWithData<List<SingleSignInCheck>>();
				
		singleSignInCheckResult.setData(signInCheckService.getSingleChecksByCycle(sign_in_check_id, cycle_time));
		
		return singleSignInCheckResult;
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/total/{sign_in_check_id}")
	public OperationResultWithData<RawSignInDataAndInfo>
			getRawDataOfTotalResult(@PathVariable("sign_in_check_id") int sign_in_check_id) {
		
		OperationResultWithData<RawSignInDataAndInfo> result = new OperationResultWithData<RawSignInDataAndInfo>();
				
		result.setData(signInCheckService.getRawResultDataAndInfo(sign_in_check_id));
		
		return result;
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/total/{sign_in_check_id}/user/{user_id}")
	public OperationResultWithData<RawSignInDataAndInfo>
			getRawDataOfTotalResult(
					@PathVariable("user_id") int user_id,
					@PathVariable("sign_in_check_id") int sign_in_check_id) {
		
		OperationResultWithData<RawSignInDataAndInfo> result = new OperationResultWithData<RawSignInDataAndInfo>();
		
		result.setData(signInCheckService.getRawResultDataAndInfo(user_id, sign_in_check_id));

		return result;

	}
	
	@ResponseBody
	@RequestMapping(path = "/total/{sign_in_check_id}/self")
	public OperationResultWithData<RawSignInDataAndInfo>
			getSelfRawDataOfTotalResult(
					@PathVariable("sign_in_check_id") int sign_in_check_id,
					HttpSession session) {
		
		int user_id = ((LoggedUser) session.getAttribute("loggedUser")).getId();
		
		return getRawDataOfTotalResult(user_id, sign_in_check_id);
		
	}
	
	@ResponseBody
	@RequestMapping(path = "/face")
	public void faceCompare(MultipartFile photo, HttpSession session) {
		
		
		
	}
	
}

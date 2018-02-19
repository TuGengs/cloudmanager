package com.halfmoon.cloudmanager.controller.web.signcheck;


import java.net.MalformedURLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.halfmoon.cloudmanager.common.Constant;
import com.halfmoon.cloudmanager.response.OperationResult;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.service.check.signcheck.SignCheckService;


@Controller
@RequestMapping("check")
@Transactional
public class CheckController {
	
	@Autowired
	private SignCheckService signCheckService;
	
	/**
	 * 检查列表
	 * @param model
	 * @return
	 * @throws MalformedURLException 
	 */
	@RequestMapping("")
	public String view(Model model,HttpSession session){
		LoggedUser user = (LoggedUser) session.getAttribute("loggedUser");
		model.addAttribute("json",signCheckService.getData(user.getId()));
		return "check1";
	}
	/**
	 * 页面跳转
	 * @return
	 */
	@RequestMapping(path="/sign/viewAdd")
	public String new_check(Model model){
		return "new-check";
	}
	/**
	 * 创建签到检查
	 * @param check_name
	 * @param second_name
	 * @param session
	 */
	@RequestMapping(path="/sign/viewAdd/post",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult new_check_post(String check_name,String second_name,HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		OperationResult result = new OperationResult();
		if(signCheckService.saveSignCheckByCreator(check_name, second_name, user_id)){
			return result;
		}
		result.setErrorMessage("网络异常");
		return result;
		
	}
	/**
	 * 用户通过四位数 来加入检查中
	 * 从sign 或者 grade 中寻找
	 * @return
	 */
	@RequestMapping(path="/join",method=RequestMethod.GET)
	@ResponseBody
	public OperationResult check_join(int type,int check_id, HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		OperationResult operation = new OperationResult();
		int result = 1;
		if(type == Constant.SIGN_IN_CHECK){
			
			result = signCheckService.join(check_id,user_id);
		}else{
			//
		}
		
		if(result == -1){
			operation.setErrorMessage("已加入这个检查");
		}else if(result == -2){
			operation.setErrorMessage("这个检查不允许加入");
		}else if(result == 0){
			operation.setErrorMessage("操作失败");
		}
		return operation;
	}
	
	@RequestMapping(path="/qrcode/join")
	public String qrcode_join(int type,int check_id, HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		int result = 1;
		if(type == Constant.SIGN_IN_CHECK){
			
			result = signCheckService.join(check_id,user_id);
		}else{
			//
		}
		if(result == 1){
			return "redirect:/sign";
		}
		return "redirect:/check";
	}
	
}

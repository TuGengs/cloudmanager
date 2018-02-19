package com.halfmoon.cloudmanager.controller.web.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.tags.Param;

import com.halfmoon.cloudmanager.common.Constant;
import com.halfmoon.cloudmanager.response.OperationResult;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.service.check.signcheck.DetailedSignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.ListCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.ShareService;
import com.halfmoon.cloudmanager.service.check.signcheck.UserViewService;


@Controller
@RequestMapping("view")
public class ViewController {
	
	@Autowired
	private UserViewService userViewService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private ListCheckService listCheckService;
	@Autowired
	private DetailedSignCheckService detailedSignCheckService;
	/**
	 * 查看主界面
	 * 从user_view获取所有的sign_in_check_id
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(path="",method=RequestMethod.GET)
	public String main(Model model,HttpSession session){
		
		LoggedUser user = (LoggedUser) session.getAttribute("loggedUser");
		int user_id = user.getId();
		model.addAttribute("list", userViewService.getCheckView(user_id, Constant.SIGN_IN_CHECK));
		return "look1";
	}
	
	
	@RequestMapping(path="/add",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult add(String account,String password,HttpSession session){
		int user_id = ((LoggedUser) session.getAttribute("loggedUser")).getId();
		int result = shareService.check(account, password, user_id);
		OperationResult operation = new OperationResult();
		if(result == -1){
			operation.setErrorMessage("账号不存在");
		}else if(result == -2){
			operation.setErrorMessage("账号或密码错误");
		}else if(result == -3){
			operation.setErrorMessage("已经存在");
		}
		return operation;
	}
	
	@RequestMapping(path="/list",method=RequestMethod.GET)
	public String list(int check_id,Integer sort_id,Model model){
		if(sort_id == null){
			sort_id = -1;
		}
		model.addAttribute("data", listCheckService.getUserByView(check_id,sort_id));
		
		return "single-check";
	}
	
	
	
}

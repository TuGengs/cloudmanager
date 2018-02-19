package com.halfmoon.cloudmanager.controller.web.signcheck;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.halfmoon.cloudmanager.response.OperationResult;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.service.check.signcheck.DetailedSignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.ListCheckService;

@RequestMapping("sign")
@Controller
@Transactional
public class SignController {
	
	@Autowired
	private ListCheckService listService;
	@Autowired
	private DetailedSignCheckService detailService;
	
	/**
	 * 签到界面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(path="",method=RequestMethod.GET)
	public String view(Model model,HttpSession session,HttpServletRequest request){
		
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		
		Gson gson = new Gson();
		
		Map<String,Object> map = listService.getSignInfo(user_id);
		model.addAttribute("map", map);
		model.addAttribute("data", gson.toJson(map));
		
		/*String url = request.getRequestURL().toString();
		Map<String,String> wechat = Sign.toResult(url);
		
		if(wechat != null){
			model.addAttribute("json", gson.toJson(wechat));	
		}else{
			model.addAttribute("json", "");
		}*/
		
		return "sign-in3";
	}
	
	/**
	 * 学生自主签到
	 * @return
	 */
	@RequestMapping(path="/submit",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult sign(int check_auto_id,
								int single_id,
								int check_id,
								double latitude,
								double longitude,
								@RequestParam(required=false)Integer serverId,
								HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		OperationResult result = new OperationResult();
		int status = detailService.handleUserSign(
				check_auto_id,single_id,check_id,latitude,longitude,serverId,user_id);
		if(status == -1){
			result.setErrorMessage("检查已经关闭");
		}else if(status == -2){
			result.setErrorMessage("距离匹配失败");
		}else if(status == 0){
			result.setErrorMessage("网络异常");
		}else if(status == -3){
			result.setErrorMessage("没有上传照片");
		}else if(status == -4){
			result.setErrorMessage("人脸匹配失败");
		}
		
		return result;
	}
}

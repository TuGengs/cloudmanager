package com.halfmoon.cloudmanager.controller.web.signcheck;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.halfmoon.cloudmanager.response.OperationResult;
import com.halfmoon.cloudmanager.response.OperationResultWithData;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.service.check.signcheck.DetailedSignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SingleSignService;

@Controller
@RequestMapping("check/sign/detail")
@Transactional
public class DetailController {
	@Autowired
	private DetailedSignCheckService detailService;
	@Autowired
	private SingleSignService singleSignService;
	
	/**
	 * 单次检查列表
	 * @param single_id
	 * @param model
	 * @return
	 */
	@RequestMapping(path="/{single_id}",method=RequestMethod.GET)
	public String detail_check(@PathVariable int single_id,
			Model model,HttpSession session,HttpServletRequest request){
		LoggedUser user = (LoggedUser) session.getAttribute("loggedUser");
		Map<String,Object> object = detailService.getUserList(single_id, user.getId());
		String url = request.getRequestURI();
		
		if(object != null){
			
			model.addAttribute("json", object);
			
			model.addAttribute("data", new Gson().toJson(object));
			
			model.addAttribute("single_id", single_id);
			
			Date date = singleSignService.get(single_id).getCreate_time();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("date", format.format(date));
			
			model.addAttribute("remark", singleSignService.getRemark(single_id));
		}else{
			model.addAttribute("data", "notOk");
		}
		
		
		return "sign-in1";
	}
	
	@RequestMapping(path="/qrcode",method=RequestMethod.GET)
	@ResponseBody
	public OperationResultWithData<String> qrcode(){
		OperationResultWithData<String> data = new OperationResultWithData<String>();
		String url = "http://www.gxykq.com/check/sign/detail/dimension/";
		data.setData(url);
		return data;
	}
	
	/**
	 * 处理学生扫描二维码签到
	 * 只有一个single_id
	 * @param single_id
	 * @param session
	 * @return
	 */
	@RequestMapping(path="/dimension/{single_id}",method={RequestMethod.GET})
	public String detail_check_mark(@PathVariable int single_id,HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		if(detailService.handleDimension(single_id, user_id)){
			// 签到完成 跳转到自主签到页面
			return "redirect:../../../../sign";
		}
		// 否则留在本页面
		return "";
	}
	
	/**
	 * 单个点击签到处理
	 * @param json
	 */
	@RequestMapping(path="/sign",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult detail_check_sign(@RequestBody Map<String,Object> map){
		OperationResult result = new OperationResult();
		
		if(detailService.handleSignSubmit(map)){
			return result;
		}
		result.setErrorMessage("");
		return result;
		//return new JSONObject().put("isOk",detailService.handleSignSubmit(map)).toString();
	}
	/**
	 * 一键全到
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/sign_all",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult detail_check_sign_all(@RequestBody Map<String,Object> map){
		OperationResult result = new OperationResult();
		if(detailService.handleSignAll(map)){
			return result;
		}
		result.setErrorMessage("");
		return result;
		//return new JSONObject().put("isOk", detailService.handleSignAll(map)).toString();
	}
	
	/**
	 * 点击头像显示详细信息
	 * @param user_id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(path="/more_info",method=RequestMethod.GET)
	@ResponseBody
	public OperationResultWithData<String> detail_check_info(int sysnum,int single_id){
		//response.setContentType("application/json;charset=utf-8");
		
		Map<String,Object> map = detailService.getUserDetail(sysnum,single_id);
		OperationResultWithData<String> result = new OperationResultWithData<String>();
		if(map != null){
			result.setData(new Gson().toJson(map));
			return result;
		}
		result.setErrorMessage("操作失败");
		return result;
	}
	/**
	 * 辅助签到
	 * @param single_id
	 * @param is_face
	 * @param is_open
	 * @param session
	 * @return
	 */
	@RequestMapping(path="/help",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult detail_check_help(int single_id,int is_face,int is_open,HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		OperationResult result = new OperationResult();
		if(detailService.handleOpenHelp(single_id, is_face, is_open, user_id)){
			return result;
		}
		result.setErrorMessage("创建者已经开启辅助检查");
		return result;
		//return new JSONObject().put("isOk", detailService.handleOpenHelp(single_id, is_face, is_open, user_id)).toString();
		
	}
	
	
	
	/**
	 * 获取经纬度信息
	 * @param single_id
	 * @param latitude
	 * @param longitude
	 * @param session
	 * @return
	 */
	@RequestMapping(path="/help/address",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult detail_check_help_address(int single_id,double latitude,double longitude,HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		OperationResult result = new OperationResult();
		if(detailService.saveAddress(single_id,latitude,longitude,user_id)){
			return result;
		}
		result.setErrorMessage("位置信息获取失败");
		return result;
		//return new JSONObject().put("isOk", detailService.saveAddress(single_id,latitude,longitude,user_id)).toString();
	}
	/**
	 * 排序的api
	 * @param sort_id
	 * @param map
	 * @return
	 */
	@RequestMapping(path="/sort",method=RequestMethod.GET,produces={"text/html;charset=UTF-8;","application/json;"})
	@ResponseBody
	public String detail_check_list_status(int sort_id,int single_id,HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		return new Gson().toJson(detailService.sorted_Info(sort_id,single_id,user_id));
		
	}
	/**
	 * 对于单个人的备注
	 * @param single_id
	 * @param user_id
	 * @param remark
	 * @return
	 */
	@RequestMapping(path="single/remark",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult detail_single_remark(int single_id,int user_id,String remark){
		OperationResult result = new OperationResult();
		if(!detailService.saveSingleRemark(single_id,user_id,remark)){
			result.setErrorMessage("保存失败");
		}
		return result;
	}
	/**
	 * 对这次检查的备注
	 * @param content
	 * @param single_id
	 * @return
	 */
	@RequestMapping(path="/all/remark",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult detail_all_remark(String content,int single_id){
		OperationResult result = new OperationResult();
		
		if(singleSignService.updateRemark(single_id,content)){
			
		}
		return result;
	}
}


package com.halfmoon.cloudmanager.controller.web.signcheck;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
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
import com.halfmoon.cloudmanager.model.check.dto.FirstView;
import com.halfmoon.cloudmanager.response.OperationResult;
import com.halfmoon.cloudmanager.response.OperationResultWithData;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.service.check.signcheck.DetailedSignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SingleSignService;
@Controller
@RequestMapping("check/sign/single")
@Transactional
public class SingleController {
	@Autowired
	private SignCheckService signCheckService;
	@Autowired
	private SingleSignService singleService;
	@Autowired
	private DetailedSignCheckService detailedSignCheckService;
	
	/**
	 * 单项检查列表
	 * @param check_id
	 * @param model
	 * @return
	 */
	@RequestMapping(path="/{check_id}",method=RequestMethod.GET)
	public String single_check(@PathVariable int check_id,Model model,HttpSession session){
		LoggedUser user = (LoggedUser) session.getAttribute("loggedUser");
		Map<String,Object> map = singleService.getAll(check_id, user.getId());
		model.addAttribute("json", map);
		model.addAttribute("check_id", check_id);
		return "check2";
		
	}
	/**
	 * 新增 提交 
	 * @param check_id
	 * @param time
	 * @param session
	 */
	@RequestMapping(path="/add",method=RequestMethod.POST)
	@ResponseBody
	public OperationResultWithData<Integer> single_check_add(int check_id,String time,HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		int result = singleService.save(check_id, time, user_id);
		OperationResultWithData<Integer> operationResult = new OperationResultWithData<Integer>();
		if(result > 0){
			operationResult.setData(result);
			
		}else{
			operationResult.setErrorMessage("保存失败");
		}
		return operationResult;
	}
	/**
	 * 删除条目
	 * @param single_id
	 * @return
	 */
	@RequestMapping(path="/delete",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult single_check_delete(int single_id){
		OperationResult result = new OperationResult();
		if(!(detailedSignCheckService.deleteBySingleId(single_id)
				&& singleService.delete(single_id))){
			result.setErrorMessage("删除失败");
		}
		return result;
	}
	
	/**
	 * 名单
	 * @param check_id
	 * @param session
	 * @return
	 */
	@RequestMapping(path="/list",method=RequestMethod.GET)
	public String single_check_list(int check_id,
			HttpSession session,Model model){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		Map<String,Object> object = singleService.getUserList(check_id, user_id);
		if(object != null){
			model.addAttribute("json", object);
		}
		
		model.addAttribute("check_id", check_id);
		return "name-list";
	}
	/**
	 * 名单列表删除
	 * @param json
	 * 这里本来用的是JSONObject来接受发现接收不到
	 */
	@RequestMapping(path="/list_delete",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult single_check_list_delete(@RequestBody Map<String,Object> map){
		OperationResult result = new OperationResult();
		if(singleService.deleteUserList(map)){
			return result;
		}
		result.setErrorMessage("");
		return result;
		//return new JSONObject().put("isOk", singleService.deleteUserList(map)).toString();
	}
	/**
	 * 生成二维码URL
	 * @param check_id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(path="/qrcode",method=RequestMethod.GET)
	@ResponseBody
	public OperationResultWithData<String> single_check_qrcode(
			int check_id,HttpServletRequest request) throws UnsupportedEncodingException{
		OperationResultWithData<String> data = new OperationResultWithData<String>();
		
		String uri = request.getRequestURI();
		int type = 0;
		if(uri.contains("sign")){
			type = 1;
		}
		String url = "http://www.gxykq.com/check/qrcode/join?type="+type+"&check_id="+check_id;
		data.setData(url);
		
		//String image_url = QrCode.getUrl(check_id);
		//data.setData(image_url);
		
		return data;
		
	}
	
	/**
	 * 设置界面 -- 所有有关公开和辅助的信息
	 * @param check_id
	 * @return
	 */
	@RequestMapping(path="/setting",method=RequestMethod.GET)
	public String single_check_public(int check_id,Model model,HttpSession session){
		// 公开信息-- 账号密码 -公开状态
		Map<String,Object> public_json = singleService.publicView(check_id);
		if(public_json != null){
			model.addAttribute("public_data", public_json);
		}
		
		
		// 公开--查看者名单
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		Map<String,Object> viewer_json = singleService.getViewerList(check_id, user_id);
		if(viewer_json != null){
			model.addAttribute("viewer_data", viewer_json);
		}
		
		
		// 辅助人员名单
		Map<String,Object> helper_json = signCheckService.getHelpers(check_id);
		if(helper_json != null){
			model.addAttribute("helper_data", helper_json);
		}		
		
		// 添加管理人员名单
		Map<String,Object> manager_json = signCheckService.getManagerList(check_id);
		if(manager_json != null){
			model.addAttribute("manager_data", manager_json);
		}
		
		Map<String,Object> map = signCheckService.getRule(check_id);
		if(!map.isEmpty()){
			model.addAttribute("cycle_data", map);
		}
		
		
		
		model.addAttribute("check_id", check_id);
		return "set";
	}
	/**
	 * 公开-密码更改
	 * @param check_id
	 * @param password
	 */
	@RequestMapping(path="/setting/public/pwd_modify",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult single_check_public_modify(int check_id,String password){
		OperationResult result = new OperationResult();
		if(singleService.publicModify(check_id, password)){
			return result;
		}
		result.setErrorMessage("更改失败");
		return result;
		//return new JSONObject().put("isOk", singleService.publicModify(check_id, password)).toString();
	}
	
	/**
	 * 公开-名单删除
	 * @param check_id
	 * @param user_id
	 */
	@RequestMapping(path="/setting/public/list_delete",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult single_check_public_list_delete(int check_id,int user_id){
		OperationResult result = new OperationResult();
		if(singleService.deleteViewer(check_id, user_id)){
			return result;
		}
		result.setErrorMessage("删除失败");
		return result;
		//return new JSONObject().put("isOk", singleService.deleteViewer(check_id, user_id)).toString();
	}
	/**
	 * 公开-与否
	 * @param check_id
	 * @param is_open
	 */
	@RequestMapping(path="/setting/public/open",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult single_check_public_open(@RequestBody Map<String,Object> map){
		int check_id = Integer.parseInt((String) map.get("check_id"));
		int is_open = (int)(double) map.get("is_open");
		OperationResult result = new OperationResult();
		if(singleService.updatePublic(check_id, is_open)){
			return result;
		}
		result.setErrorMessage("操作失败");
		return result;
	}
	/**
	 * 辅助设置 -手机号获取信息
	 * @param tel
	 * @return
	 */
	@RequestMapping(path="/setting/help/tel_get",method=RequestMethod.GET)
	@ResponseBody
	public OperationResultWithData<String> single_check_help_tel(String tel){
		Map<String,Object> map = signCheckService.getUserInfoByTel(tel);
		OperationResultWithData<String> data = new OperationResultWithData<String>();
		if(map != null){
			data.setData(new Gson().toJson(map).toString());
		}else{
			data.setErrorMessage("Error");
		}
		return data;
	}
	/**
	 * 分配辅助人员-提交
	 * @param json
	 */
	@RequestMapping(path="/setting/help/submit",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult single_check_help_post(@RequestBody Map<String,Object> map,HttpSession session){
		LoggedUser user = (LoggedUser)session.getAttribute("loggedUser");
		int user_id = user.getId();
		OperationResult result = new OperationResult();
		List<Integer> list = new ArrayList<Integer>();
		for(String num : (List<String>)map.get("manager")){
			list.add(Integer.parseInt(num));
		}
		int status = signCheckService.saveHelper(
				Integer.parseInt((String)map.get("check_id")),Integer.parseInt((String)map.get("sysnum")),
				list,(String)map.get("manager_class"),user_id);
		if(status == 0){
			result.setErrorMessage("不能对本人进行操作");
		}else if(status == -1){
			result.setErrorMessage("操作失败");
		}
		return result;
		//return new JSONObject().put("isOk", signCheckService.saveHelper(map,user_id)).toString();
	}
	
	/**
	 * 删除辅助人员
	 * @param check_id
	 * @param user_id
	 * @return
	 */
	@RequestMapping(path="/setting/help/delete",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult single_check_help_delete(int check_id,int user_id){
		
		if(signCheckService.deleteHelper(check_id, user_id)){
			return new OperationResult();
		}
		
		return new OperationResult("删除失败");
	}
	
	
	/**
	 * 开始时间，周期天数，扣分规则
	 * @return
	 */
	@RequestMapping(path="/setting/cycle/submit",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult single_check_cycle(@RequestBody Map<String,Object> map){
		if(signCheckService.handleCycle(map)){
			return new OperationResult();
		}
		return new OperationResult("操作失败");
	}
	/**
	 * 设置 编辑课程
	 * @param check_id
	 * @param model
	 * @return
	 */
	@RequestMapping(path="/setting/edit")
	public String single_check_edit(int check_id,Model model){
		FirstView view = signCheckService.getDName(check_id);
		model.addAttribute("data", view);
		model.addAttribute("check_id", check_id);
		return "new-check";
	}
	
	/**
	 * 更新提交
	 * @param check_id
	 * @param check_name
	 * @param second_name
	 * @return
	 */
	@RequestMapping(path="/setting/edit/submit")
	@ResponseBody
	public OperationResult single_check_edit_submit(
					@Param("check_id")int check_id,
					@Param("check_name")String check_name,
					@Param("second_name")String second_name){
		
		if(signCheckService.updateDName(check_id,check_name,second_name)){
			return new OperationResult();
		}
		return new OperationResult("提交错误");
		
	}
	/**
	 * 设置 解散课程
	 * @param check_id
	 * @return
	 */
	@RequestMapping(path="/setting/dis",method=RequestMethod.POST)
	public String single_check_quit(int check_id){
		
		if(signCheckService.delete(check_id)){
			return "redirect:../../../";
		}
		return "";
	}
}


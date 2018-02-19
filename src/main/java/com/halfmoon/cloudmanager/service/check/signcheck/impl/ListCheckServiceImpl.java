package com.halfmoon.cloudmanager.service.check.signcheck.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.halfmoon.cloudmanager.common.Constant;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.ListCheckDao;
import com.halfmoon.cloudmanager.model.check.ListCheck;
import com.halfmoon.cloudmanager.model.check.Sign;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.check.dto.UUInfo;
import com.halfmoon.cloudmanager.service.check.signcheck.ListCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignService;
import com.halfmoon.cloudmanager.service.user.impl.UserService;
/**
 * 
 * @author xiaogao.XU
 *
 */
@Service
public class ListCheckServiceImpl implements ListCheckService{
	
	@Autowired
	private ListCheckDao listCheckDao;
	
	@Autowired
	private UserService userService;
	@Autowired
	private SignService signService;
	@Autowired
	private SignCheckService signCheckService;
	
	@Override
	public void save(ListCheck listCheck) {
		listCheckDao.add(listCheck);
	}

	@Override
	public void updateAutoId(int check_id, int auto_id,int check_type, List<Integer> list) {
		
		listCheckDao.updateAutoId(check_id, auto_id,check_type , list);
	}

	/*@Override
	public JSONObject getAvilByCheckID(int check_id,int check_type) {
		// 获取剩余学号
		List<String> sysList = getUserIds(check_id, check_type);
		JSONArray array = new JSONArray();
		for(String sysnum : sysList){
			UInfo user = userService.getUserNames(sysnum);
			JSONObject object = new JSONObject();
			object.put(user.getClass_name(), 
					new JSONObject().put(String.valueOf(sysnum), user.getName()));
			array.put(object);
		}
		
		return new JSONObject().put("results", array);
	}*/
/*
	@Override
	public List<Integer> getUserIds(int check_id, int check_type) {
		return null;
	}*/

	@Override
	public List<UInfo> getUserInfo(int check_id,int auto_id,int type,int single_id,int order) {
		return listCheckDao.getAllIds(check_id, auto_id, type, single_id, order);
	}

	@Override
	public Map<String,Object> getCheckInfo(int user_id) {
		List<ListCheck> check_list = listCheckDao.getChecksBySys(user_id);
		List<Object> array = new ArrayList<Object>();
		for(ListCheck listCheck : check_list){
			// 得到了check_id check_auto_id check_type
			// 要去sign表中寻找对应的签到是否开启
			// 先找单项检查的即check_id=check_auto_id的状态
			// 再寻找对应的
			// 如果所有的签到都没有开启 status=2 其实这里还是要有那种未签到的状态 从签到表中找到最近一次打开的签到，对应自己有没有签到成功 
			// 如果有签到是开着的 而且这个签到肯定唯一 继续
			// 如果name_list中已经存在了就是已经签到了 status=1
			// 没存在就是status=0 
			int status = 0;
			int check_id = listCheck.getCheck_id();
			int check_auto_id = listCheck.getAuto_id();
			
			Map<String,Object> object = new HashMap<String,Object>();
			if(listCheck.getCheck_type() == Constant.SIGN_IN_CHECK){
				// SIGN操作  获取这个检查ID中的有开启辅助检查的Sign single_id 变化 其他ID不变
				Sign sign = signService.getByBossOpen(check_id);
				if(sign != null){
					// 有开启的
					listCheck.setAuto_id(check_id);
				}else{
					sign = signService.getByHelperOpen(check_id, check_auto_id);
					if(sign == null){
						// 都没有开启的
						status = 2;
					}else{
						// 有开启的就继续
					}
				}
				
				if(status != 2){
					// 找到了sign
					JsonParser parser = new JsonParser();
					JsonArray json = parser.parse(sign.getName_list().toString()).getAsJsonArray();
					
					if(json.contains(parser.parse(String.valueOf(user_id)))){
						status = 1;
					}else{
						status = 0;
					}
					object.put("is_face", sign.getIs_face());
					object.put("latitude", sign.getLatitude());
					object.put("longitude", sign.getLongitude());
				}
				object.put("check_auto_id", check_auto_id);
				object.put("check_id", check_id);
				object.put("status", status);
				// 获取名字
				String name = signCheckService.getNameByID(check_auto_id);
				object.put("check_name", name);
			}else{
				
			}
			array.add(object);
		} 
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("data", array);
		return map;
	}

	/*@Override
	public List<UInfo> getUserInfo(int check_id, int check_auto_id, int type) {
		List<Integer> list;
		if(check_id == check_auto_id){
			// 创建者调用
			list = getUserIds(check_id,type);
		}else{
			list = getUserIDs(check_id, check_auto_id, type);
		}
		List<UInfo> userList = new ArrayList<UInfo>();
		for(int sysnum : list){
			UInfo userIcon = userService.getUserIcons(sysnum);
			userList.add(userIcon);
		}
		return userList;
	}*/

	@Override
	public void deleteUserList(int check_id, List<Integer> list,int type) {
		listCheckDao.deleteUserList(check_id, list,type);
	}



	@Override
	public int getCount(int check_id, int auto_id, int type) {
		return listCheckDao.getCount(check_id, auto_id, type);
	}

	@Override
	public List<UUInfo> getUserIDs(int check_id, int check_auto_id, int type) {
		return listCheckDao.getByAutoId(check_id, check_auto_id, type);
	}

	@Override
	public Map<String,Object> getSignInfo(int user_id) {
		// 获取自己有的检查
		List<ListCheck> list = listCheckDao.getSignInfo(user_id);
		// 根据这些检查获取检查开启信息，找到开启的和开启人
		// 只有创建者和分配辅助检查我的人才能开启我这个
		List<Object> array = new ArrayList<Object>();
		for(ListCheck check : list){
			int check_id = check.getCheck_id();
			int check_auto_id = check.getAuto_id();
			
			if(check.getCheck_type()==Constant.SIGN_IN_CHECK){
				String check_name = signCheckService.getNameByID(check_id);
				// 签到检查
				// 先看创建者是否开启了
				// sign.getStatusByCreator 获取is_open 当sign_check_id==auto_id
				// 同时两者check_id == check_id
				// sign.getStatusByHelper 获取is_open 当sign_check_id!=auto_id
				
				//  只需要从check_id判断有开启签到的即可
				Map<String,Object> object = new HashMap<String,Object>();
				
				Sign sign = signService.getSignBySingleIdAndOpen(-1, -1, check_id,1);
				System.out.println("sign"+sign);
				int status = -1;
				if(sign != null){
					check_auto_id = sign.getSign_in_check_auto_id();
					int single_id = sign.getSingle_sign_check_id();
					int is_face = sign.getIs_face();
					status = 0;
					if(sign.getName_list() != null){
						JsonParser parser = new JsonParser();
						JsonArray temp = parser.parse(sign.getName_list().toString()).getAsJsonArray();
						if(temp.contains(parser.parse(user_id+""))){
							status = 1;
						}
					}
					object.put("single_id", single_id);
					object.put("is_face", is_face);
				}
				
				int _user_id = signCheckService.getUserId(check_auto_id);
				String name = userService.getName(_user_id);
				object.put("check_id", check_id);
				object.put("check_auto_id", check_auto_id);
				object.put("status", status);
				
				object.put("check_name", check_name);
				object.put("name", name);
				array.add(object);
				
			}else{
				
			}
		}
		Map<String,Object> object = new HashMap<String,Object>();
		object.put("list", array);
		return object;
		
	}

	@Override
	public int isExists(ListCheck listCheck) {
		return listCheckDao.isExists(listCheck);
	}
	@Override
	public boolean deleteByCheckId(int check_id,int type){
		return listCheckDao.deleteByCheckId(check_id,type);
	}

	@Override
	public boolean updateAutoId(int check_id, int auto_id, int type) {
		return listCheckDao.updateAutoIdForCheckId(check_id,auto_id,type);
	}

	@Override
	public List<UUInfo> getUserByView(int check_id,int sort_id) {
		return listCheckDao.getUserByView(check_id,sort_id);
	}

	@Override
	public List<UUInfo> getList(int auto_id, int check_id, int type) {
		return listCheckDao.getList(auto_id, check_id, type);
	}
	
	
}

package com.halfmoon.cloudmanager.service.check.signcheck.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halfmoon.cloudmanager.common.Constant;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.SingleSignDao;
import com.halfmoon.cloudmanager.model.check.DetailedSignInCheck;
import com.halfmoon.cloudmanager.model.check.SingleSignInCheck;
import com.halfmoon.cloudmanager.model.check.dto.FirstView;
import com.halfmoon.cloudmanager.model.check.dto.ShareInfo;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.check.dto.UUInfo;
import com.halfmoon.cloudmanager.service.check.signcheck.DetailedSignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.ListCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.ShareService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignService;
import com.halfmoon.cloudmanager.service.check.signcheck.SingleSignService;
import com.halfmoon.cloudmanager.service.check.signcheck.UserViewService;
import com.halfmoon.cloudmanager.service.user.impl.UserService;
/**
 * 
 * @author xiaogao.XU
 *
 */
@Service
public class SingleSignServiceImpl implements SingleSignService {
	@Autowired
	private SingleSignDao dao;
	
	@Autowired
	private SignCheckService signCheckService;
	
	@Autowired
	private ListCheckService listCheckService;
	
	@Autowired
	private DetailedSignCheckService detailedSignCheckService;
	
	@Autowired
	private SignService signService;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserViewService userViewService;
	
	@Override
	public void save(SingleSignInCheck single_Sign_Check) {
		dao.add(single_Sign_Check);
	}

	@Override
	public void update(SingleSignInCheck single_Sign_Check) {
		dao.modify(single_Sign_Check);
	}

	@Override
	public SingleSignInCheck get(int id) {
		return (SingleSignInCheck) dao.get(id);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public Map<String,Object> getAll(int check_id,int user_id) {
		List<SingleSignInCheck> list =  dao.getAll(check_id);
		// 自增ID
		int auto_id = signCheckService.getAutoID(check_id, user_id);
		// 获取检查是否是开启着的
		List<Object> array = new ArrayList<Object>();
		for (SingleSignInCheck check : list){
			Map<String,Object> object = new HashMap<String,Object>();
			int single_id = check.getId();
			object.put("id", single_id);
			// 这个检查是否被开启
			if(signService.isOpen(single_id, auto_id)){
				object.put("is_open", 1);
			}else{
				object.put("is_open", 0);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			object.put("time", sdf.format(check.getCreate_time()));
			array.add(object);
		}
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("list", array);
		
		int type = 0;
		if(auto_id == check_id){
			type = 1;
		}
		// 这个人是不是创建者
		json.put("type", type);
		
		FirstView view =  signCheckService.getDName(auto_id);
		json.put("check_name", view.getCheck_name());
		json.put("second_name",view.getSecond_name());
		
		return json;
	}

	@Override
	public void save(int check_id) {
		SingleSignInCheck check = new SingleSignInCheck();
		check.setSign_in_check_id(check_id);
		check.setCreate_time(new Date());
		
		save(check);
	}

	@Override
	public int save(int check_id, String create_time,int user_id) {
		try {
			
			SingleSignInCheck check = new SingleSignInCheck();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if(create_time == null || create_time.equals("")){
				check.setCreate_time(new Date());	
			}else{
				check.setCreate_time(sdf.parse(create_time));
			}
			
			check.setSign_in_check_id(check_id);
			save(check);
			
			/** 
			 * 	detail_sign_check 插入数据
			 *  首先从list_to_check中取数据
			 *  判断是创建者还是辅助者
			 */
			List<UInfo> userList = listCheckService.getUserInfo(check_id, -1,Constant.SIGN_IN_CHECK,-1,0);
			List<DetailedSignInCheck> detai_list = new ArrayList<DetailedSignInCheck>();
			for(UInfo userIcon : userList){
				DetailedSignInCheck detailedSignCheck = new DetailedSignInCheck(
						check.getId(),userIcon.getUser_id(),userIcon.getName(),
						0,userIcon.getIcon_url(),check_id);
				detailedSignCheck.setManager_id(user_id);
				detai_list.add(detailedSignCheck);
			}
			detailedSignCheckService.saveList(detai_list);
			return check.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		
	}

	@Override
	public int getCheckId(int single_id) {
		return dao.getCheck_id(single_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getUserList(int check_id, int sysnum) {
		
		List<UUInfo> list;
		// 根据check_id和check_auto_id获取对应值
		// 在别的地方也会有调用getUserList但情景不一样，需求也就不一样
		// 这个为什么有这么多的if判断，以为写的时间太久了，我自己都不记得了。。。
		if(sysnum == 0){
			// 创建者 则只需通过check_id获取
			list = listCheckService.getList(-1, check_id, Constant.SIGN_IN_CHECK);
		}else if(sysnum == -1){
			list = listCheckService.getList(check_id, check_id, Constant.SIGN_IN_CHECK);
		}else{
			// 获取自增ID
			int check_auto_id = signCheckService.getAutoID(check_id, sysnum);
			if(check_auto_id == check_id){
				list = listCheckService.getList(-1, check_id, Constant.SIGN_IN_CHECK);
			}else{
				list = listCheckService.getList(check_auto_id, check_id, Constant.SIGN_IN_CHECK);
			}
		}
		if(list == null || list.size() == 0){
			return null;
		}
		Map<String,Object> array = new HashMap<String, Object>();
		// 遍历处理数据
		for(UUInfo userInfo : list){
			String class_name = userInfo.getClass_name();
			
			if(array.containsKey(class_name)){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("sysnum", userInfo.getSysnum());
				map.put("name", userInfo.getName());
				map.put("user_id",userInfo.getUser_id());
				List<Object> temp =(List<Object>) array.get(class_name);
				temp.add(map);
				
			}else{
				List<Object> new_array = new ArrayList<Object>();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("sysnum", userInfo.getSysnum());
				map.put("name", userInfo.getName());
				map.put("user_id",userInfo.getUser_id());
				new_array.add(map);
				array.put(class_name, new_array);
			}
		}
		List<Object> class_array = new ArrayList<Object> ();
		
		for(Entry<String, Object> entry : array.entrySet()){
			String class_name = entry.getKey();
			Map<String,Object> object = new HashMap<String,Object>();
			object.put("class_name", class_name);
			object.put("list", entry.getValue());
			object.put("num", ((List<Object>)entry.getValue()).size());
			class_array.add(object);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", class_array);
		return map;
	}

	@Override
	public boolean deleteUserList(Map<String,Object> map) {
		try {
			int check_id = (int)(double) map.get("check_id");
			@SuppressWarnings("unchecked")
			List<Integer> array =  (List<Integer>) map.get("list");
			if(array.size() == 0){
				return true;
			}
			/*List<Integer> list = new ArrayList<Integer>();
			// 将jsonarray转成list
			for(int i = 0;i<array.size();i++){
				list.add(array.get(i));
			}*/
			// 将业务给listCheckService做
			listCheckService.deleteUserList(check_id, array,Constant.SIGN_IN_CHECK);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Map<String,Object> publicView(int check_id) {
		// 从shareService取得数据处理一下即可
		ShareInfo info = shareService.getInfo(check_id, Constant.SIGN_IN_CHECK);
		if(info == null){
			return null;
		}
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("account", info.getAccount());
		json.put("password", info.getPassword());
		json.put("status", info.getIs_open());
		return json;
	}

	@Override
	public boolean publicModify(int check_id, String password) {
		try {
			shareService.updatePwd(check_id, password, Constant.SIGN_IN_CHECK);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Map<String,Object> getViewerList(int check_id,int sysnum) {
		List<UUInfo> list = userViewService.getViewerList(check_id, Constant.SIGN_IN_CHECK);
		if(list == null || list.size() == 0){
			//  返回空值信息这个后面统一写
			return null;
		}
		
		List<Object> array = new ArrayList<Object>();
		for(int i = 0;i<list.size();i++){
			UUInfo info = list.get(i);
			int user_id = info.getUser_id();
			// 
			if(user_id == sysnum){
				continue;
			}
			Map<String,Object> object = new HashMap<String,Object>();
			object.put("name", info.getName());
			object.put("sysnum", info.getSysnum());
			object.put("user_id", user_id);
			array.add(object);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", array);
		return map;
	}

	@Override
	public boolean deleteViewer(int check_id, int sysnum) {
		try {
			userViewService.deleteViewer(check_id, sysnum, Constant.SIGN_IN_CHECK);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updatePublic(int check_id, int is_open) {
		try {
			shareService.updateStatus(check_id, is_open, Constant.SIGN_IN_CHECK);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Integer> getSingleId(int check_id) {
		return dao.getSingleId(check_id);
	}
	
	@Override
	public boolean deleteByCheckId(int check_id){
		return dao.deleteByCheckId(check_id);
	}

	@Override
	public boolean updateRemark(int single_id, String content) {
		
		return dao.updateRemark(single_id,content);
	}

	@Override
	public String getRemark(int single_id) {
		return dao.getRemark(single_id);
	}


}

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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.halfmoon.cloudmanager.common.Constant;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.SignCheckDao;
import com.halfmoon.cloudmanager.model.check.DetailedSignInCheck;
import com.halfmoon.cloudmanager.model.check.ListCheck;
import com.halfmoon.cloudmanager.model.check.Share;
import com.halfmoon.cloudmanager.model.check.SignInCheck;
import com.halfmoon.cloudmanager.model.check.UserView;
import com.halfmoon.cloudmanager.model.check.dto.FirstView;
import com.halfmoon.cloudmanager.model.check.dto.SignHelp;
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
 * 签到检查
 * @author xiaogao.XU
 *
 */
@Service
public class SignCheckServiceImpl implements SignCheckService{
	
	@Autowired
	private SignCheckDao checkDao;
	
	@Autowired
	private UserViewService userViewService;
	
	@Autowired
	private ListCheckService listCheckService;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SingleSignService single_Service;
	@Autowired
	private DetailedSignCheckService detailService;
	@Autowired
	private SignService signService;
	
	@Override
	public SignInCheck get(int id) {
		return (SignInCheck) checkDao.get(id);
	}
	
	@Override
	public void save(SignInCheck signCheck) {
		checkDao.add(signCheck);
	}
	
	@Override
	public boolean delete(int check_id) {
		// deleteByCheckId;
		try {
			checkDao.deleteByCheckId(check_id);
			userViewService.deleteByCheckId(check_id, Constant.SIGN_IN_CHECK);
			listCheckService.deleteByCheckId(check_id, Constant.SIGN_IN_CHECK);
			shareService.deleteByCheckId(check_id, Constant.SIGN_IN_CHECK);
			single_Service.deleteByCheckId(check_id);
			detailService.deleteByCheckId(check_id);
			signService.deleteByCheckId(check_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 保存之后立即更新check_id 使其和id保持一致
	 */
	@Override
	public boolean saveSignCheckByCreator(String check_name,String second_name,int user_id) {
		try{
			// save
			SignInCheck signCheck = new SignInCheck(check_name,second_name, user_id,new Date(), Constant.CREATOR, Constant.ALLOW);
			save(signCheck);
			// check_id
			int id = signCheck.getId();
			updateCheckId(id, id);
			// user_view
			userViewService.save(new UserView(user_id,id,Constant.SIGN_IN_CHECK));
			// share
			Share share = new Share(id,shareService.generate(id),"123",Constant.SIGN_IN_CHECK);
			shareService.save(share);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
			
		
		
	}
	
	@Override
	public void updateCheckId(int checkId, int id) {
		checkDao.updateCheckId(checkId, id);
	}
	
	@Override
	public JsonObject getAllByUserId(int user_id) {
		List<FirstView> list = checkDao.getAllByUserId(user_id);
		if(list == null || list.size() == 0){
			return null;
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list);
		JsonObject object = new JsonObject();
		object.add("sign_check", element);
		return object;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int saveHelper(int check_id,int sysnum,List<Integer> manager,String manager_class,int user_id) {
		try {
			//int check_id = Integer.parseInt((String)map.get("check_id"));
			//int sysnum = (int)(double)map.get("sysnum");
			
			// 判断一下是否是自己给自己辅助，然后判断这个人是否已经分配了辅助，如果分配过了就重新分配
			if(sysnum == user_id){
				return 0;
			}
			SignInCheck check = new SignInCheck();
			check.setCheck_id(check_id);
			check.setCreator_id(sysnum);
			check.setChecker_type((byte)Constant.FOLLOWER);
			check.setIs_allowed((byte)Constant.ALLOW);
			check.setSub_name(manager_class);
			check.setCreate_time(new Date());
			check.setName(getNameByID(check_id));
			int check_auto_id = getAutoID(check_id, sysnum);
			if(check_auto_id == 0){// 没有这个记录
				// 填充实体
				save(check);
				// 保存到userView中 自己查看自己 
				userViewService.save(new UserView(check.getCreator_id(),check.getId(),Constant.SIGN_IN_CHECK));
			}else{
				check.setId(check_auto_id);
				checkDao.modify(check);
			}
			
			// setSelected(sign_in_check_id, check.getCheck_list());
			
			
			// 将list_to_check中对应的自增ID更新
			System.out.println("check_id"+check_id+"check_auto_id"+check.getId()+" "+manager);
			if(manager.size() > 0){
				listCheckService.updateAutoId(check_id,check.getId(),Constant.SIGN_IN_CHECK,manager);	
			}
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	@Override
	public int join(int check_id,int user_id) {
		try {
			
			// 判断这个检查是否允许加入
			if(get(check_id).getIs_allowed() != Constant.ALLOW){
				// 这个检查已经不允许加入了
				return -2;
			}
			// 判断这个人是否已经加入这个检查了
			ListCheck check = new ListCheck(check_id, user_id, check_id, Constant.SIGN_IN_CHECK);
			int count = listCheckService.isExists(check);
			if(count > 0 ){
				// 已经存在了
				return -1;
			}
			listCheckService.save(check);
			// 并且填充detailedCheck
			// 先根据check_id 获取对应的所有single_id
			List<Integer> list = single_Service.getSingleId(check_id);
			//UInfo userInfo = userService.getUserIcons(user_id);
			int manager_id = getUserId(check_id);
			List<DetailedSignInCheck> check_list = new ArrayList<DetailedSignInCheck>();
			List<Integer> single_list = detailService.getSingleIdByUserIdAndCheckId(user_id,check_id);
			if(list != null && list.size() > 0){
				for(int single_id : list){
					if(single_list != null 
							&& single_list.contains(single_id)){
							continue;
					}
					DetailedSignInCheck detailedSignCheck = new DetailedSignInCheck(
							single_id,user_id,"",
							0,"",check_id);
					detailedSignCheck.setManager_id(manager_id);
					check_list.add(detailedSignCheck);
					
				}
				detailService.saveList(check_list);
			}
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	@Override
	public String getNameByID(int check_auto_id) {
		return checkDao.getNameByID(check_auto_id);
	}
	@Override
	public int getAutoID(int check_id, int user_id) {
		return checkDao.getAutoId(check_id, user_id);
	}
	@Override
	public Map<String,Object> getHelpers(int check_id) {
		// 先根据检查ID获取所有的自增ID、creator_id、sub_name
		List<SignHelp> helper_list = checkDao.getSignHelps(check_id);
		if(helper_list == null || helper_list.size() == 0){
			return null;
		}
		// 根据creator_id获取姓名
		
		// 先抽取出creator_id为List
		List<Integer> creator_list = new ArrayList<Integer>();
		// 根据自增ID获取对应的人数
		// 抽取出自增ID为List
		List<Integer> auto_list = new ArrayList<Integer>();
		List<String> sub_name_list = new ArrayList<String>();
		// 分离
		for(SignHelp helper : helper_list){
			creator_list.add(helper.getCreator_id());
			auto_list.add(helper.getCheck_auto_id());
			sub_name_list.add(helper.getSub_name());
		}
		// 从User中获取姓名
		List<String> user_name_list = userService.getUserName(creator_list);
		// 从list_to_check中获取人数
		// List<Integer> num_list = listCheckService.getCount(check_id, auto_list,Constant.SIGN_IN_CHECK);
		// 整合
		List<Object> array = new ArrayList<Object>();
		for(int i = 0;i<creator_list.size();i++){
			Map<String,Object> object = new HashMap<String,Object>();
			int num = listCheckService.getCount(check_id, auto_list.get(i), Constant.SIGN_IN_CHECK);
			object.put("manager_class", sub_name_list.get(i));
			object.put("sysnum", creator_list.get(i));
			object.put("name", user_name_list.get(i));
			object.put("num", num);
			array.add(object);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", array);
		return map;
	}
	
	@Override
	public Map<String,Object> getManagerList(int check_id, int user_id) {
		// 首先获取自增ID
		int check_auto_id = getAutoID(check_id, user_id);
		// 根据自增ID和check_id获取人员sysnum
		List<UUInfo> sys_list = listCheckService.getUserIDs(check_id, check_auto_id, Constant.SIGN_IN_CHECK);
		if(sys_list == null || sys_list.size() == 0){
			return null;
		}
		
		List<Object> array = new ArrayList<Object>();
		for(int index = 0;index<sys_list.size();index++){
			UUInfo info = sys_list.get(index);
			Map<String,Object> object = new HashMap<String,Object>();
			object.put("sysnum", info.getSysnum());
			object.put("name", info.getName());
			object.put("user_id",info.getUser_id());
			array.add(object);
		}
		Map<String,Object> object = new HashMap<String,Object>();
		object.put("data", array);
		return object;
	}
	@Override
	public Map<String,Object> getUserInfoByTel(String tel) {
		UInfo info = userService.getUserInfoByTel(tel);
		if(info == null){
			// 如果没有数据则返回
			return null;
		}
		Map<String,Object> object = new HashMap<String,Object>();
		object.put("sysnum", info.getUser_id());
		object.put("name", info.getName());
		return object;
	}
	
	@Override
	public Map<String,Object> getManagerList(int check_id) {
		return single_Service.getUserList(check_id, -1);
	}
	
	@Override
	public List<FirstView> getData(int user_id) {
		return checkDao.getAllByUserId(user_id);
	}
	
	
	@Override
	public int getUserId(int check_auto_id) {
		return checkDao.getUserId(check_auto_id);
	}
	
	@Override
	public FirstView getDName(int check_id) {
		return checkDao.getDName(check_id);
	}
	@Override
	public boolean updateDName(int check_id, String check_name,
			String second_name) {
		return checkDao.updateCheckName(check_id,check_name) 
				&& checkDao.updateSubName(check_id, second_name);
	}

	@Override
	public boolean handleCycle(Map<String, Object> map) {
		/**
		 * {"late":{"1":2,}}
		 */
		try {
			if(map.containsKey("time")||map.containsKey("date")){
				// 
				checkDao.updateCycle(map);
				
			}else{
				System.out.println(map.toString());
				int id = Integer.parseInt((String)map.get("check_id"));
				SignInCheck check = checkDao.get(id);
				String rule = check.getRule();
				JsonObject json = null;
				JsonParser parser = new JsonParser();
				if(rule != null){
					json = parser.parse(rule.toString()).getAsJsonObject();
				}else{
					json = new JsonObject();
				}
				String key = (String) map.get("item");
				
				if((int)(double)map.get("status")==1){
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>)map.get("rule");
					int times = Integer.parseInt(list.get(0));
					float score = Float.parseFloat(list.get(1));
					// 增加
					if(!json.has(key)){
						JsonObject temp = new JsonObject();
						temp.addProperty(times+"", score);
						json.add(key, parser.parse(temp.toString()));
					}else{
						json.get(key).getAsJsonObject().addProperty(times+"", score);
					}
					
				}else{
					String times = (String) map.get("rule");
					// 删除
					json.get(key).getAsJsonObject().remove(times);
				}
				check.setRule(json.toString());
				checkDao.modify(check);
				
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public Map<String,Object> getRule(int check_id) {
		SignInCheck check = checkDao.get(check_id);
		String rule = check.getRule();
		Map<String,Object> map = new HashMap<String,Object>();
		
		// 开始日期 天数
		Date date = check.getStart_time();
		if(date != null){
			map.put("start", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		}
		
		int k = check.getCycle();
		if(k == 0){
			map.put("date", "无周期");
		}else if(k == -1){
			map.put("date", "一个月");
		}else{
			map.put("date", k);
		}
		
		if(rule != null){
			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(rule).getAsJsonObject();
			
			for(Entry<String, JsonElement> entry: json.entrySet()){
				String key = entry.getKey();
				JsonObject object = entry.getValue().getAsJsonObject();
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				for(Entry<String, JsonElement> item : object.entrySet()){
					Map<String,Object> m = new HashMap<String, Object>();
					String child_key = item.getKey();
					double value = object.get(child_key).getAsDouble();
					m.put("key", Integer.parseInt(child_key));
					m.put("value", value);
					list.add(m);
				}
				map.put(key, list);
			}
			
		}
		/*{"late":{"1":4},"absence":{"1":3,"4":3,"9":4}}*/
		return map;
	}

	@Override
	public boolean deleteHelper(int check_id, int user_id) {
		try {
			// 首先从sign_in_check表删除
			int auto_id = getAutoID(check_id, user_id);
			checkDao.delete(auto_id);
			// 把list_to_check表中的auto_id 改为 check_id
			listCheckService.updateAutoId(check_id, auto_id, Constant.SIGN_IN_CHECK);
			// 删除user_view
			userViewService.delete(auto_id,user_id,Constant.SIGN_IN_CHECK);
			// 删除 sign
			signService.deleteByAutoId(auto_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
}

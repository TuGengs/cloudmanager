package com.halfmoon.cloudmanager.service.check.signcheck.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.support.logging.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.halfmoon.cloudmanager.common.Constant;
import com.halfmoon.cloudmanager.dao.sql.check.signcheck.impl.DetailedSignCheckDao;
import com.halfmoon.cloudmanager.model.check.DetailedSignInCheck;
import com.halfmoon.cloudmanager.model.check.Sign;
import com.halfmoon.cloudmanager.model.check.dto.LackTimes;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.check.dto.UserDetail;
import com.halfmoon.cloudmanager.service.check.signcheck.DetailedSignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.ListCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignCheckService;
import com.halfmoon.cloudmanager.service.check.signcheck.SignService;
import com.halfmoon.cloudmanager.service.check.signcheck.SingleSignService;
import com.halfmoon.cloudmanager.service.user.impl.UserService;
import com.halfmoon.cloudmanager.util.AddressUtil;
import com.halfmoon.cloudmanager.util.Bsearch;
import com.halfmoon.cloudmanager.util.face.FaceplusUtils;
import com.halfmoon.cloudmanager.util.upload.FaceUtil;
@Repository
public class DetailedSignCheckServiceImpl implements DetailedSignCheckService{
	@Autowired
	private DetailedSignCheckDao detailDao;
	@Autowired
	private UserService userService;
	@Autowired
	private SingleSignService singleService;
	@Autowired
	private SignCheckService signCheckService;
	@Autowired
	private ListCheckService listService;
	@Autowired
	private SignService signService;
	
	@Override
	public Map<String,Object> getUserList(int single_id, int user_id) {
		
		int check_id = singleService.getCheckId(single_id);
		int check_auto_id = signCheckService.getAutoID(check_id, user_id);
		List<UInfo> list = null;
		Sign sign = null;
		
		if(check_id == check_auto_id){
			list = listService.getUserInfo(check_id, -1, Constant.SIGN_IN_CHECK, -1, 0);
			sign = signService.get(single_id, check_id);
			
		}else{
			list = listService.getUserInfo(check_id, check_auto_id, Constant.SIGN_IN_CHECK,-1, 0);
			sign = signService.get(single_id, check_auto_id);
		}
		
		if(list == null || list.size() == 0){
			return null;
		}
		
		Map<String,Object> json = toJSON(list, single_id);
	
		long time = 0;
		if(sign != null && sign.getIs_open() == 1){
			if(sign.getOpen_time() != null){
				Date date = new Date();
				long d1 = date.getTime();
				long d2 = sign.getOpen_time().getTime();
				time = (d1 - d2) / 1000;
			}
		}
		json.put("time", time);
		
		
		
		return json;
	}


	@Override
	public boolean handleSignSubmit(Map<String, Object> map) {
		try {
			int single_id = Integer.parseInt((String) map.get("single_id"));
			int user_id = Integer.parseInt((String) map.get("sysnum"));
			int status = (int)(double) map.get("status");
			String tip = (String) map.get("tip");
			int ought = detailDao.getSingleLackTimes(single_id, user_id);
			if(status == 3){
				// 到了
				detailDao.updateStatus(single_id, user_id, ought, status, tip);
			}else if(status == 0){
				// 缺到
				detailDao.updateStatus(single_id, user_id, 0, status, tip);
			}else{
				detailDao.updateStatusWithoutOught(single_id, user_id, status, tip);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Map<String,Object> getUserDetail(int user_id,int single_id) {
		try {
			UserDetail user = detailDao.getUserDetail(user_id,single_id);
			Map<String,Object> object = new HashMap<String,Object>();
			object.put("class", user.getClass_name());
		    object.put("gender", user.getGender());
		    object.put("tel", user.getTel());
		    object.put("remark", user.getRemark());
		    object.put("img_url", user.getImg_url());
			return object;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	

	@Override
	public void save(DetailedSignInCheck detailedSignCheck) {
		detailDao.add(detailedSignCheck);
	}

	@Override
	public boolean handleSignAll(Map<String, Object> map) {
		try {
			int single_id = Integer.parseInt((String) map.get("single_id"));
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) map.get("sysnums");
			for(String sysnum : list){
				int user_id  = Integer.parseInt(sysnum);
				int ought = detailDao.getOughtTimes(single_id, user_id);
				
				detailDao.updateStatus(single_id, user_id, ought, 3, "");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public boolean handleOpenHelp(int single_id, int is_face, int is_open,
			int user_id) {
		int check_id = singleService.getCheckId(single_id);
		int check_auto_id = signCheckService.getAutoID(check_id, user_id);
		if(is_open == 1){
			return openHelp(check_id, single_id, check_auto_id,is_face);
		}else{
			return closeHelp(check_id, single_id, check_auto_id);
		}
	}
	
	@Override
	public boolean openHelp(int check_id, int single_check_auto_id,
			int check_auto_id,int is_face) {
		try {
			
			if(check_auto_id == check_id){
				// 创建者  关闭自己的，关闭辅助者的
				// 把自己的全部关闭
				signService.closeSelf(check_id, check_auto_id);
				// boss 开启的时候其他人的自动关闭 single_id
				signService.closeAll(check_id, single_check_auto_id);
			}else{
				// 创建者这一次如果开启了
				if(signService.getSignBySingleIdAndOpen(single_check_auto_id,check_id,-1,1)!=null){
					return false;
				}
				signService.closeSelf(check_id, check_auto_id);
			}
			Sign sign = signService.get(single_check_auto_id, check_auto_id);
			if(sign != null){
				sign.setIs_open(1);
				sign.setIs_face(is_face);
				sign.setOpen_time(new Date());
				sign.setName_list(null);
				signService.update(sign);
			}else{
				Sign new_sign = new Sign();
				new_sign.setIs_face(is_face);
				new_sign.setIs_open(1);
				new_sign.setOpen_time(new Date());
				new_sign.setSign_in_check_auto_id(check_auto_id);
				new_sign.setSign_in_check_id(check_id);
				new_sign.setSingle_sign_check_id(single_check_auto_id);
				signService.save(new_sign);
				
			}
			// 将应到次数加- 并且将is_here 置为 1未全到
			detailDao.updateOughtTimes(single_check_auto_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean closeHelp(int check_id, int single_check_auto_id,
			int check_auto_id) {
		try {
			signService.closeSelfOnce(single_check_auto_id, check_auto_id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public void updateOughtTimes(int single_check_id) {
		detailDao.updateOughtTimes(single_check_id);
	}

	@Override
	public int handleUserSign(int check_auto_id,
			int single_id,int check_id,double latitude,
			double longitude,Integer serverId,int user_id) {
		try {
		
			
			Sign sign = signService.get(single_id, check_auto_id);
			if(sign == null || sign.getIs_open() == 0){
				return -1; // 辅助检查已经关闭
			}
			// 还要判断是否已经上传了照片
			boolean is_upload = false;
			if(is_upload){
				return -3;
			}
			
			double distance = AddressUtil.getDistance(latitude, longitude, sign.getLatitude(), sign.getLongitude());
			if(distance >= Constant.DISTANCE){
				return -2; // 距离太远
			}
			/*if(serverId != null){
				byte[] bytes = FaceUtil.getFaceByte(serverId);
				// 人脸匹配
			}
			// 照片
			if(!FaceplusUtils.faceCompare("", null)){
				return -4;// 人脸不匹配
			}*/
			
			String object = sign.getName_list();
			JsonArray array = null;
			JsonParser parser = new JsonParser();
			
			if(object != null){
				array = parser.parse(object.toString()).getAsJsonArray();
			}else{
				array = new JsonArray();
			}
			array.add(user_id);
			sign.setName_list(array.toString());
			signService.update(sign);
			int times = detailDao.getSingleLackTimes(single_id, user_id);
			int status = times<=1?3:1;
			detailDao.handleSign(single_id, user_id,status);
			
			return 1;
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0; // 异常位置情况
		}
	
		
		
	}

	@Override
	public int is_Here(int single_id, int user_id) {
		// TODO Auto-generated method stub
		return detailDao.is_Here(single_id, user_id);
	}

	@Override
	public Map<String,Object> sorted_Info(int sort_id,int single_id,int user_id) {
		try {
			int check_id = singleService.getCheckId(single_id);
			int check_auto_id = signCheckService.getAutoID(check_id, user_id);
			List<UInfo> list = null;
			int status = -1;
			if(check_id != check_auto_id){
				status = check_auto_id;
			}
			switch (sort_id) {
			case 0:
				list = listService.getUserInfo(check_id, status, Constant.SIGN_IN_CHECK,single_id, 3);
				break;
			case 1:
				list = listService.getUserInfo(check_id, status, Constant.SIGN_IN_CHECK,-1, 1);
				break;
			case 2:
				list = listService.getUserInfo(check_id, status, Constant.SIGN_IN_CHECK,-1, 2);
				break;
			case 3:
				// 缺到次数???
				
				
				list = listService.getUserInfo(check_id, status, Constant.SIGN_IN_CHECK,-1, 4);
				System.out.println(list);
				List<LackTimes> times = detailDao.getUserIdSortByLackTimes(check_id, list);
				List<UInfo> temp = new ArrayList<UInfo>();
				System.out.println(times);
				for(LackTimes item : times){
					// 要寻找的学号
					int id = item.getUser_id();
					temp.add(Bsearch.bsearch(list, 0, list.size(), id));
				}
				list = temp;
				System.out.println("list"+list);
				break;

			}
			
			Map<String,Object> map = toJSON(list, single_id);
			map.put("time", 0);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> toJSON(List<UInfo> list, int single_id) {
		try {
			Map<String,Object> object = new HashMap<String,Object>();
			List<Object> array = new ArrayList<Object>();
			int check_id = singleService.getCheckId(single_id);
			int lack_sum = 0;
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> class_json = new HashMap<String,Object> ();
			int index = 0;
			int class_id = 0;
			for(UInfo userInfo : list){ // 遍历用户信息
				int user_id = userInfo.getUser_id();
				// 获取缺到次数
				int lack_num = detailDao.getLackTimes(check_id, user_id);
				// 当前的签到状态
				int status = detailDao.is_Here(single_id, user_id);
				Map<String,Object> json = new HashMap<String,Object> ();
				if(status == 0){
					lack_sum ++;
				}
				String class_name = userInfo.getClass_name();
				if(!map.containsKey(class_name)){
					// 第一次遇到这个班级
					class_id = ++index;
					
					Map<String,Object> class_object = new HashMap<String,Object> ();
					class_object.put("class_name", class_name);
					class_object.put("class_id", class_id);
					List<Object> class_list =new ArrayList<Object>(); 
					class_list.add(1);class_list.add(status==0?1:0);
					class_list.add(status==0?"100%":"0%");
					class_json.put(class_id+"",class_list);
					map.put(class_name, class_object);
				}else{
					class_id = (int) ((Map<String,Object>)map.get(class_name)).get("class_id");
					
					List<Object> class_array = (List<Object>)class_json.get(class_id+"");
					int sum = (int)class_array.get(0)+1;
					class_array.set(0,sum);
					
					
					int class_lack_num = (int)class_array.get(1)+(status==0?1:0);
					class_array.set(1,class_lack_num);
					String class_lack_rate = 100*(int) (float)class_lack_num/sum+"%";
					class_array.set(2,class_lack_rate);
				}
				json.put("user_id", user_id);
				json.put("sysnum", userInfo.getSysnum());
				json.put("class_id", class_id);
				json.put("lack_num", lack_num);
				json.put("icon_url", userInfo.getIcon_url());
				json.put("name", userInfo.getName());
				json.put("status", status);;
				
				array.add(json);
				
			}
			String lack_rate = 100*(int) (float)lack_sum/list.size()+"%";
			Map<String,Object> class_map = new HashMap<String,Object>();
			class_map.put("sum",list.size());
			class_map.put("lack_num", lack_sum);
			class_map.put("lack_rate", lack_rate);
			object.put("status",class_map);
			object.put("list", array);
			
			object.put("option", map.values());
			object.put("notOk", class_json);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}

	@Override
	public boolean saveAddress(int single_id, double latitude,
			double longitude, int user_id) {
		try {
			int check_id = singleService.getCheckId(single_id);
			int check_auto_id = signCheckService.getAutoID(check_id, user_id);
			signService.updateAddress(check_auto_id, single_id, latitude, longitude);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public boolean saveList(List<DetailedSignInCheck> list) {
		
		return detailDao.saveList(list);
	}

	@Override
	public boolean handleDimension(int single_id, int user_id) {
		try {
			// 根据single_id获取开启的签到Sign 此时让check_auto_id为-1
			Sign sign = signService.getSignBySingleIdAndOpen(single_id,-1,-1,1);
			if(sign == null){
				return false;
			}
			
			String object = sign.getName_list();
			JsonArray array = null;
			JsonParser parser = new JsonParser();
			if(object != null){
				array = parser.parse(object.toString()).getAsJsonArray();
			}else{
				array = new JsonArray();
			}
			if(array.contains(parser.parse(String.valueOf(user_id)))){
				// 如果已经签到了就返回
				return true;
			}
			array.add(user_id);
			sign.setName_list(array.toString());
			signService.update(sign);
			int times = detailDao.getSingleLackTimes(single_id, user_id);
			int status = times<=1?3:1;
			
			detailDao.handleSign(single_id, user_id,status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public boolean deleteByCheckId(int check_id){
		return detailDao.deleteByCheckId(check_id);
	}

	@Override
	public List<Integer> getSingleIdByUserIdAndCheckId(int user_id, int check_id) {
		return detailDao.getSingleIdByUserIdAndCheckId(user_id,check_id);
	}


	@Override
	public boolean deleteBySingleId(int single_id) {
		return detailDao.deleteBySingleId(single_id);
	}


	@Override
	public boolean saveSingleRemark(int single_id, int user_id, String remark) {
		return detailDao.saveSingleRemark(single_id,user_id,remark);
	}
	
}
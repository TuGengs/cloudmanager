package com.halfmoon.cloudmanager.service.check.signcheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.halfmoon.cloudmanager.model.check.ListCheck;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.check.dto.UUInfo;
/**
 * 检查对应表Service
 * @author xiaogao.XU
 *
 */
public interface ListCheckService {
	
	public void save(ListCheck listCheck);
	/**
	 * 更新自增ID
	 * @param check_id 检查ID
	 * @param auto_id 要更新的自增ID
	 * @param array 要更新的对象 JSON
	 */
	public void updateAutoId(int check_id,int auto_id,int check_type,List<Integer> array);
	/**
	 * 获取检查ID对应的所有sysnum
	 * @param check_id 检查ID
	 * @param isAll true 获取所有的，false 未被选中的
	 * @return Integer
	 */
	/*public List<Integer> getUserIds(int check_id,int check_type);*/
	/**
	 * 通过检查ID获取用户姓名头像
	 * @param check_id
	 * @return UserIcon
	 */
	/*public List<UInfo> getUserInfo(int check_id,int check_type);*/
	
	/**
	 * 获取未被分配的学生列表
	 * @param check_id 检查ID
	 * @return json {"results":[{class_name:{sysnum:name}}]}
	 */
	/*public JsonObject getAvilByCheckID(int check_id,int check_type);*/
	/**
	 * 用户要签到的页面给前端的数据
	 * @param sysnum
	 * @return {"data":[{"check_auto_id":"2345","check_id":"2344",
	 * "check_name":"早检","status":0,
	 * "is_face":0,"latitude":"","longitude",""},{}]}
	 * status 0:开启但还未签到 1:已签到 2:签到关闭，未签到
	 */
	public Map<String, Object> getCheckInfo(int user_id);
	
	/**
	 * 获取单人对应的人员列表
	 * 用来传递给第三个界面的数据
	 * @param check_id
	 * @param check_auto_id
	 * @param type
	 * @return
	 */
	/*public List<UInfo> getUserInfo(int check_id,int check_auto_id,int type);*/
	
	/**
	 * 名单删除
	 * 用于single_sign_checkService的删除
	 * @param check_id 
	 * @param list 要删除的人员sysnum
	 * @param type 标明是sign还是grade
	 */
	public void deleteUserList(int check_id,List<Integer> list,int type);
	

	/**
	 * 根据check_id和check_auto_id获取对应的人数
	 * 单个获取
	 * @param check_id
	 * @param auto_id
	 * @param type
	 * @return
	 */
	public int getCount(int check_id,int auto_id,int type);
	/**
	 * 获取对应的所有用户IDs
	 * @param check_id
	 * @param check_auto_id
	 * @param type
	 * @return
	 */
	public List<UUInfo> getUserIDs(int check_id,int check_auto_id,int type);
	
	
	public Map<String, Object> getSignInfo(int user_id);
	
	public int isExists(ListCheck listCheck);
	boolean deleteByCheckId(int check_id, int type);
	public boolean updateAutoId(int check_id, int auto_id, int type);
	
	/**
	 * 统一的方法 获取数据
	 * @param check_id
	 * @param auto_id
	 * @param type
	 * @param single_id
	 * @param order
	 * @return
	 */
	List<UInfo> getUserInfo(int check_id, int auto_id, int type, int single_id,
			int order);
	
	/**
	 * 默认就是signcheck
	 * @param check_id
	 * @param sort_id 0班级 1学号 2姓名 3缺到次数
	 * @return
	 */
	List<UUInfo> getUserByView(int check_id, int sort_id);
	/**
	 * 获取对应人员列表
	 * type 1 | 0 | -1
	 * auto > -1 && check_id > -1 是按照对应人来 
	 * 适用于name-list
	 * auto < -1 && check_id > -1 检查中所有的人
	 * @param auto_id
	 * @param check_id
	 * @param type
	 * @return
	 */
	List<UUInfo> getList(int auto_id,int check_id,int type);
	
}

package com.halfmoon.cloudmanager.service.check.signcheck;

import java.util.List;
import java.util.Map;







import com.google.gson.JsonObject;
import com.halfmoon.cloudmanager.model.check.SignInCheck;
import com.halfmoon.cloudmanager.model.check.dto.FirstView;
/**
 * 
 * @author xiaogao.XU
 *
 */
public interface SignCheckService {
	
	/**
	 * /api/check/
	 * 检查第一个界面 
	 * 根据用户获取所有检查
	 * @param userId 用户sysnum
	 * @return SignCheck List
	 */
	public JsonObject getAllByUserId(int user_id);
	
	public List<FirstView> getData(int user_id);
	
	/**
	 * 创建签到检查 /api/check/sign/add/
	 * 创建者-保存
	 * 顺便把自己保存在user_view中
	 * 创建SignCheck时 check_id 和 自增id 一致
	 * 顺便生成一个默认的分享
	 * @param check_name 检查名称
	 * @param user_id 创建者sysnum
	 */
	public boolean saveSignCheckByCreator(String check_name,String second_name,int user_id);
	
	/**
	 * 设置-分配辅助/api/check/sign/single/setting/help/
	 * 获取辅助签到人员信息
	 * 从签到表中获取sub_name
	 * 从list_to_check中获取分配的人数
	 * @param check_id
	 * @return
	 */
	public Map<String, Object> getHelpers(int check_id);
	
	/**
	 * 设置-分配辅助 管理者名单/api/check/sign/single/setting/help/list/
	 * @param check_id
	 * @param sysnum 前端传过来的sysnum
	 * @return
	 */
	public Map<String, Object> getManagerList(int check_id,int user_id);
	
	/**
	 * 设置-分配辅助 添加管理人员 手机号获取/api/check/sign/single/setting/help/tel/
	 * 根据手机号获取用户信息
	 * @param tel
	 * @return
	 */
	public Map<String, Object> getUserInfoByTel(String tel);
	/**
	 * 设置-分配辅助 添加管理人员 管理者名单
	 * 这里得到的是创建者的名单，可以说是总数据，
	 * 也是从list_to_check中获取
	 * 怎么判断该人员是否已经分配
	 * 我的想法的是list_to_check有sign_check_id和sign_check_auto_id
	 * 首先未分配的check_id和check_auto_id是一致的 如果分配出去的话就将check_auto_id改成相应的值
	 * 如果是创建者获取名单列表就只通过check_id来进行获取
	 * @param check_id
	 * @return
	 */
	public Map<String, Object> getManagerList(int check_id);
	
	/**
	 * 设置-分配辅助 添加管理人员 提交/api/check/sign/single/setting/help/submit/
	 * 保存辅助检查人员的信息
	 * 并将这个人保存到user_view中
	 * 并根据check_list中的数据更新list_to_check表 添加自增ID进去
	 * @param json 存放数据 sysnum name person
	 */
	public int saveHelper(int check_id,int sysnum,List<Integer> manager,String manager_class,int user_id);
	
	/**
	 * 删除辅助人员
	 * @param check_id
	 * @param user_id
	 * @return
	 */
	public boolean deleteHelper(int check_id,int user_id);
	
	/**
	 * 删除所有数据
	 * sign_in_check表删除 check_id=#{check_id}
	 * single 获取所有single_id 删除所有数据
	 * detailed 删除single_id=#{single_id}
	 * userview 删除
	 * list_to_check
	 * share_check
	 * sign
	 * @param id
	 * @return
	 */
	public boolean delete(int check_id);
	
	
	
	
	public SignInCheck get(int id);
	
	public void save(SignInCheck signCheck);
	/**
	 * 获取自增ID
	 * 主要用来判断该检查是辅助检查还是自己创建的
	 * @param check_id
	 * @param sysnum
	 * @return id
	 */
	public int getAutoID(int check_id,int user_id);
	
	
	/**
	 * 更新表里的检查ID
	 * @param checkId 要更新的检查ID
	 * @param id 对应的自增ID
	 */
	public void updateCheckId(int checkId,int id);
	
	
	
	
	/**
	 * 查看有没有匹配的四位码,并返回check_id
	 * 如果匹配，就将这个人加入到list_to_check中
	 * @param code 四位码
	 * @return 检查ID 0为不匹配
	 */
	public int join(int check_id,int user_id);
	
	
	/**
	 * 获取检查的名字
	 * @param check_auto_id
	 * @return String
	 */
	public String getNameByID(int check_auto_id);
	
	
	public int getUserId(int check_auto_id);
	
	public FirstView getDName(int check_id);

	public boolean updateDName(int check_id, String check_name,
			String second_name);
	
	
	public boolean handleCycle(Map<String,Object> map);
	
	public Map<String, Object> getRule(int check_id);
	
}

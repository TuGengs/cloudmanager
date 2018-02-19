package com.halfmoon.cloudmanager.dao.sql.user;

import java.util.List;

import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.user.User;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.response.user.UserInfo;

/**
 * 
 * 用户相关的数据库操作的接口
 * @author hzq
 *
 */
public interface IUserDao {
	/**
	 * 已经爬取数据的情况下用这种方法注册（仅支持江西农大）
	 * @param user 用户对象
	 * @return 操作结果
	 */
	public boolean registerWithData(User user);
	/**
	 * 验证用户手机号、密码是否匹配
	 * @param phone 手机号
	 * @return 密码
	 */
	public LoggedUser validate(String tel, String hashedPassword);
	
	/**
	 * 修改密码
	 * @param tel 手机号
	 * @param newHashedPassword 新密码
	 * @return 操作是否成功
	 */
	public boolean resetPassword(String tel, String newHashedPassword);
	
	/**
	 * 将用户头像和用户关联起来
	 * @param id 用户id
	 * @param icon_id 头像id
	 * @return 操作是否成功
	 */
	public boolean relateWithIcon(int id, int icon_id);
	
	/**
	 * 获取用户信息
	 * @param id 用户id
	 * @return 用户信息
	 */
	public UserInfo getUserInfo(int id);
	
	/**
	 * 获取姓名和班级名称
	 * @param user_id
	 * @return
	 */
	public UInfo getUserDList(int user_id);
	/**
	 * 获取姓名和头像
	 * @param sysnum
	 * @return sysnum name icon_url class_name
	 */
	public UInfo getUserIcon(int user_id);
	
	
	public List<String> getUserName(List<Integer> list);
	
	public UInfo getUserInfoByTel(String tel);
	public String getName(int user_id);
	
	public List<UInfo> getUserInfoByStatus(int single_id,List<Integer> list);
	
	public List<UInfo> getUserInfoBySys(int single_id,List<Integer> list);
	public List<UInfo> getUserInfoByName(int single_id,List<Integer> list);
	public List<UInfo> getUserInfoWithoutSort(List<Integer> list);
	
}

package com.halfmoon.cloudmanager.service.user;

import java.util.List;

import com.halfmoon.cloudmanager.model.check.dto.UserDetail;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.user.User;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.response.user.UserInfo;

/**
 * 用户相关业务逻辑接口
 * @author hzq
 *
 */
public interface IUserService {
	
	/**
	 * 已经爬取数据的情况下用这种方法注册（仅支持江西农大）
	 * @param user 用户对象
	 * @return 操作结果
	 */
	public boolean registerWithData(User user);
	
	/**
	 * 注册
	 * @param tel 手机号
	 * @param password 密码
	 * @return 是否成功
	 */
	public boolean register(User user);
	
	/**
	 * 登录
	 * @param user 用户数据
	 * @return 登录成功的用户，失败则为null
	 */
	public LoggedUser login(String tel, String password);
	
	/**
	 * 获取用户信息
	 * @param id 用户id
	 * @return 用户信息
	 */
	public UserInfo getUserInfo(int id);
	
	/**
	 * 修改个人信息
	 * @param user 修改后的用户（不包含id、password）
	 * @return 是否成功
	 */
	public boolean modifyProfile(User user);

	/**
	 * 修改密码
	 * @param tel 手机号
	 * @param new_password 新密码
	 * @return 是否成功
	 */
	public boolean resetPassword(String tel, String new_password);

	/**
	 * 上传头像
	 * @param id 用户id
	 * @param icon_url 头像图片的url
	 * @return
	 */
	public boolean uploadIcon(int id, String icon_url);
	
	/**
	 * 上传签到照
	 * @param id 用户id
	 * @param icon_url 签到照的url
	 * @return
	 */
	public boolean uploadPhoto(int id, String photo_url);
	
	/**
	 * 获取
	 * @param user_id
	 * @return
	 */
	public UInfo getUserNames(int user_id);

	public UInfo getUserIcons(int user_id);
	

	public List<String> getUserName(List<Integer> list);

	public UInfo getUserInfoByTel(String tel);
	
	public String getName(int user_id);
	
	public List<UInfo> getUserInfoByStatus(int single_id,List<Integer> list);
	
	public List<UInfo> getUserInfoBySys(int single_id,List<Integer> list);
	
	public List<UInfo> getUserInfoByName(int single_id,List<Integer> list);
	
	public List<UInfo> getUserInfoWithoutSort(List<Integer> list);
	
}

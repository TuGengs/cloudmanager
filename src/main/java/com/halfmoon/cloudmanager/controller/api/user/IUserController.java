package com.halfmoon.cloudmanager.controller.api.user;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.halfmoon.cloudmanager.model.user.User;
import com.halfmoon.cloudmanager.response.OperationResult;

/**
 * 用户的webapi接口
 * @author hzq
 *
 */
public interface IUserController {

	/**
	 * 注册
	 * @return 操作结果
	 */
	public OperationResult register(User user, BindingResult result, HttpSession session);
	
	/**
	 * 修改用户资料
	 * @param user 用户
	 * @param result 后台验证的结果（输入信息是否有误）
	 * @return 操作结果
	 */
	public OperationResult modifyProfile(User user, BindingResult result, HttpSession session);
	
	/**
	 * 修改密码
	 * @param id 用户id
	 * @param new_password 新密码
	 * @param session 就是session
	 * @return 操作结果
	 */
	public OperationResult resetPassword(String tel, String new_password, HttpSession session);
	
	/**
	 * 登录
	 * @param tel 手机号
	 * @param password 密码
	 * @param session 就是session
	 * @return
	 */
	public OperationResult login(String tel, String password, HttpSession session);
	
	/**
	 * 退出登录
	 * @param session 就是session
	 * @return 操作结果
	 */
	public OperationResult logout(HttpSession session);

	/**
	 * 上传头像
	 * @param icon 头像文件
	 * @param session 就是session
	 * @return 操作结果
	 */
	public OperationResult uploadIcon(MultipartFile icon, HttpSession session);
	
	/**
	 * 登录教务系统验证账号密码是否正确
	 * @param user 用户对象
	 * @param eas_password 教务系统密码
	 * @return 操作结果（是否成功登录）
	 */
	public OperationResult EASRegister(User user, String eas_password, HttpSession session);
	
}

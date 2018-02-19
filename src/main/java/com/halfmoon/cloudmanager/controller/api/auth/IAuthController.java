package com.halfmoon.cloudmanager.controller.api.auth;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.halfmoon.cloudmanager.response.OperationResult;

/**
 * 验证码的相关操作，包括图片验证码、短信验证码的生成
 * 和验证
 * @author hzq
 *
 */
public interface IAuthController {
	
	/**
	 * 获取图片验证码
	 * @param session 不解释
	 * @param response 不解释
	 */
	public void getPictureAuthcode(HttpSession session, HttpServletResponse response);
	
	/**
	 * 验证图片验证码
	 * @param userCode 用户输入的验证码
	 * @return 验证结果（若失败则包含原因）
	 */
	public OperationResult validatePictureAuthCode(HttpSession session, String userInput);
	
	/**
	 * 获取短信验证码
	 * @param session 不解释
	 * @param tel 电话号码
	 */
	public OperationResult getSMSAuthcode(HttpSession session, String tel);
	
	/**
	 * 验证短信验证码
	 * @param code 用户输入的验证码
	 * @return 验证结果（若失败则包含原因）
	 */
	public OperationResult validateSMSAuthcode(HttpSession session, String userInput);
	
}

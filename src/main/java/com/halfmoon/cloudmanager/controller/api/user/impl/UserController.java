package com.halfmoon.cloudmanager.controller.api.user.impl;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.halfmoon.cloudmanager.controller.api.user.IUserController;
import com.halfmoon.cloudmanager.controller.api.user.UserValidator;
import com.halfmoon.cloudmanager.model.user.User;
import com.halfmoon.cloudmanager.model.user.photo.Photo;
import com.halfmoon.cloudmanager.response.OperationResult;
import com.halfmoon.cloudmanager.response.OperationResultWithData;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.response.user.UserInfo;
import com.halfmoon.cloudmanager.service.user.impl.UserService;
import com.halfmoon.cloudmanager.util.eas.LoginUtil;
import com.halfmoon.cloudmanager.util.upload.IconFileUtil;
import com.halfmoon.cloudmanager.util.upload.PhotoFileUtil;

/**
 * 用户账户的相关网页api
 * @author hzq
 *
 */
@Controller
@RequestMapping("account")
public class UserController implements IUserController {
	
	private static final String SMALL_ICON_PREFIX = "icon_small";
	private static final String LARGE_ICON_PREFIX = "icon_large";
	private static final String PHOTO_PREFIX = "photo";
	
	IconFileUtil iconFileUtil = new IconFileUtil();
	PhotoFileUtil photoFileUtil = new PhotoFileUtil();

	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(new UserValidator());
	}
	
	@ResponseBody
	@RequestMapping(path = "/checkTel")
	public OperationResultWithData<Boolean> checkTel(String tel) {
		
		OperationResultWithData<Boolean> result = new OperationResultWithData<Boolean>();
		
		boolean used = userService.checkoutIfTelHadBeenUsed(tel);
		
		result.setData(used);
		
		return result;
		
	}

	@Override
	@ResponseBody
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public OperationResult register(@Validated User user, BindingResult result, HttpSession session) {
		
		String tel = user.getTel();
		String password = user.getPassword();
		user.setOpenid((String) session.getAttribute("openid"));
		
		if(tel == null || password == null || result.hasErrors()) {
			return new OperationResult("注册信息填写有误，请返回修改！");
		}
		
		if(userService.register(user)) {
			return new OperationResult();
		}
		
		return new OperationResult("注册失败，请稍候再试！");
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/modify/profile", method = RequestMethod.POST)
	public OperationResult modifyProfile(
			@Validated User user, BindingResult result, HttpSession session) {
		
		if(result.hasErrors()) {
			return new OperationResult("您输入的信息有误，请检查后重新修改！");
		}
		
		int id = ((LoggedUser) session.getAttribute("loggedUser")).getId();
		user.setId(id);
		
		if(userService.modifyProfile(user)) {
			// 更新session中的用户信息
			LoggedUser loggedUser = userService.refreshProfile(id);
			session.setAttribute("loggedUser", loggedUser);
			return new OperationResult();
		}
		
		return new OperationResult("个人信息修改失败，请稍候再试！");
		
	}
	
	@ResponseBody
	@RequestMapping(path = "/profile", method = RequestMethod.POST)
	public OperationResultWithData<UserInfo> getProfile(int id) {
		
		OperationResultWithData<UserInfo> result = new OperationResultWithData<UserInfo>();
		
		UserInfo userInfo = userService.getUserInfo(id);
		if(userInfo == null) {
			result.setErrorMessage("获取用户信息失败！");
		}
		
		result.setData(userInfo);
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(path = "/profile/self")
	public OperationResultWithData<UserInfo> getSelfProfile(HttpSession session) {
		
		int id = ((LoggedUser) session.getAttribute("loggedUser")).getId();
		
		OperationResultWithData<UserInfo> result = new OperationResultWithData<UserInfo>();
		
		UserInfo userInfo = userService.getUserInfo(id);
		if(userInfo == null) {
			result.setErrorMessage("获取用户信息失败！");
		}
		
		result.setData(userInfo);
		
		return result;
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/reset/password", method = RequestMethod.POST)
	public OperationResult resetPassword(String tel, String new_password, HttpSession session) {
		
		if(userService.resetPassword(tel, new_password)) {
			return new OperationResult();
		}
		
		return new OperationResult("密码修改失败，请稍候再试！");
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/uploadIcon", method = RequestMethod.POST)
	public OperationResult uploadIcon(
			@RequestParam("icon") MultipartFile icon, HttpSession session) {
		
		int id = ((LoggedUser) session.getAttribute("loggedUser")).getId();
		
		String small_icon_path = iconFileUtil.makePath(icon, SMALL_ICON_PREFIX);
		String large_icon_path = iconFileUtil.makePath(icon, LARGE_ICON_PREFIX);
		
		String fileName = iconFileUtil.generateFileName(icon);
		
		OperationResult failure = new OperationResult("头像上传失败，请稍候再试！");
		
		try {
			
			iconFileUtil.compressAndSave(icon, small_icon_path + fileName, 50, 50);
			iconFileUtil.compressAndSave(icon, large_icon_path + fileName, 500, 500);
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return failure;
		}
		
		if (userService.uploadIcon(id, small_icon_path + fileName)) {
			return new OperationResult();
		}
	
		return failure;
		
	}
	
	@ResponseBody
	@RequestMapping(path = "/uploadPhoto", method = RequestMethod.POST)
	public OperationResult uploadPhoto(
			@RequestParam("photo") MultipartFile photo, HttpSession session) {
		
		OperationResult failure = new OperationResult("签到照片上传失败，请稍候再试！");
		
		String photo_path = null;
		String fileName = null;
				
		try {
			
			// 检测签到照中是否有人脸，是否只有一张人脸
			int faceNum = userService.detectFaceNum(photo.getBytes());
			if(faceNum == 0) {
				return new OperationResult("很抱歉，您上传的照片中未检测到人脸，请重新拍照并上传！");
			}
			if(faceNum > 1) {
				return new OperationResult("照片中检测到了" + faceNum + "张人脸，请确保照片中只有您一个人。请重新拍照并上传！");
			}
			
			photo_path = photoFileUtil.makePath(photo, PHOTO_PREFIX);
			fileName = photoFileUtil.generateFileName(photo);
			
			photoFileUtil.compressAndSave(photo, photo_path + fileName, 720, 1280);
		
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return failure;
		}
		
		int id = ((LoggedUser) session.getAttribute("loggedUser")).getId();
		if(userService.uploadPhoto(id, photo_path + fileName)) {
			return new OperationResult();
		}
		
		return failure;
		
	}
	
	@ResponseBody
	@RequestMapping(path = "/photo", method = RequestMethod.POST)
	public OperationResultWithData<Photo> getUserPhoto(int id) {
		
		OperationResultWithData<Photo> result = new OperationResultWithData<Photo>();
		
		result.setData(userService.getPhoto(id));
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(path = "/photo/self")
	public OperationResultWithData<Photo> getSelfUserPhoto(HttpSession session) {
		
		int id = ((LoggedUser) session.getAttribute("loggedUser")).getId();
		
		OperationResultWithData<Photo> result = new OperationResultWithData<Photo>();
		
		result.setData(userService.getPhoto(id));
		
		return result;
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public OperationResultWithData<LoggedUser>
			login(String tel, String password, HttpSession session) {
		
		OperationResultWithData<LoggedUser> loginResult = new OperationResultWithData<LoggedUser>();
		
		LoggedUser loggedUser = userService.login(tel, password);
		
		if(loggedUser == null) {
			loginResult.setErrorMessage("您输入的用户名或密码错误！");
			return loginResult;
		}
		
		// 把用户信息放入session中
		session.setAttribute("loggedUser", loggedUser);
		
		loginResult.setData(loggedUser);
		
		return loginResult;
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public OperationResult logout(HttpSession session) {
		
		session.removeAttribute("loggedUser");
		
		return new OperationResult();
		
	}
	
	/**
	 * 目前仅支持江西农大
	 * @param sysnum
	 * @param password
	 */
	@Override
	@ResponseBody
	@RequestMapping(path = "/EASRegister", method = RequestMethod.POST)
	public OperationResult EASRegister(User user, String eas_password, HttpSession session) {
		
		LoginUtil loginUtil = new LoginUtil();
		try {
			
			if(!loginUtil.login(user.getSysnum(), eas_password)) {
				return new OperationResult("教务系统用户名或密码错误！");
			}
			
			user.setOpenid((String) session.getAttribute("openid"));
			userService.registerWithData(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new OperationResult("教务系统登录失败！");
		}
		
		return new OperationResult();
		
	}
	
}

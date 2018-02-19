package com.halfmoon.cloudmanager.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halfmoon.cloudmanager.common.Server;
import com.halfmoon.cloudmanager.dao.sql.user.icon.impl.IconDao;
import com.halfmoon.cloudmanager.dao.sql.user.impl.UserDao;
import com.halfmoon.cloudmanager.dao.sql.user.photo.impl.PhotoDao;
import com.halfmoon.cloudmanager.model.check.dto.UInfo;
import com.halfmoon.cloudmanager.model.user.User;
import com.halfmoon.cloudmanager.model.user.icon.Icon;
import com.halfmoon.cloudmanager.model.user.photo.Photo;
import com.halfmoon.cloudmanager.response.user.LoggedUser;
import com.halfmoon.cloudmanager.response.user.UserInfo;
import com.halfmoon.cloudmanager.service.user.IUserService;
import com.halfmoon.cloudmanager.util.encrypt.MD5Util;
import com.halfmoon.cloudmanager.util.face.FaceplusUtils;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private IconDao iconDao;
	@Autowired
	private PhotoDao photoDao;
	
	@Override
	public boolean registerWithData(User user) {

		// 先将用户密码加密
		user.setPassword(MD5Util.encrypt(user.getPassword()));

		return userDao.registerWithData(user);

	}

	@Override
	public boolean register(User user) {

		// 先将用户密码加密
		user.setPassword(MD5Util.encrypt(user.getPassword()));

		return userDao.add(user);

	}

	@Override
	public LoggedUser login(String tel, String password) {

		// 先将用户密码加密
		String hashed_password = MD5Util.encrypt(password);

		return userDao.validate(tel, hashed_password);

	}

	public UserInfo getUserInfo(int id) {

		return userDao.getUserInfo(id);

	}
	
	public LoggedUser refreshProfile(int id) {

		return userDao.refresh(id);

	}
	
	public LoggedUser loginThroughWechat(String openid) {
		
		return userDao.getLoggedUserByOpenid(openid);
		
	}

	@Override
	public boolean modifyProfile(User user) {

		return userDao.modify(user);

	}

	@Override
	public boolean resetPassword(String tel, String new_password) {

		// 先将用户密码加密
		String hashed_password = MD5Util.encrypt(new_password);

		return userDao.resetPassword(tel, hashed_password);

	}

	@Override
	public boolean uploadIcon(int id, String icon_url) {

		Icon icon = new Icon();
		icon.setUrl(Server.getHomepageUrl() + icon_url);
		icon.setUser_id(id);

		if (!iconDao.add(icon)) {
			return false;
		}
		int icon_id = icon.getId();

		return userDao.relateWithIcon(id, icon_id);

	}
	
	public int detectFaceNum(byte[] imgBuff) {
		return FaceplusUtils.faceDetect(imgBuff);
	}
	
	public boolean uploadPhoto(int id, String photo_url) {
		
		Photo photo = new Photo();
		photo.setUrl(Server.getHomepageUrl() + photo_url);
		photo.setUser_id(id);
		
		if(!photoDao.add(photo)) {
			return false;
		}
		int photo_id = photo.getId();
		
		return userDao.relateWithPhoto(id, photo_id);
		
	}
	
	public boolean isSamePerson(int id, byte[] imgBuff) {
		
		Photo photo = photoDao.get(id);
		String photoUrl = Server.getHomepageUrl() + photo.getUrl();
		
		return FaceplusUtils.faceCompare(photoUrl, imgBuff);
		
	}
	
	public boolean bindOpenid(int id, String openid) {
		return userDao.relateWithOpenid(id, openid);
	}
	
	public boolean unbindOpenid(int id) {
		return userDao.removeOpenid(id);
	}
	
	public Photo getPhoto(int user_id) {
		return photoDao.get(user_id);
	}
	
	public boolean checkoutIfTelHadBeenUsed(String tel) {
		return userDao.checkoutIfTelHadBeenUsed(tel);
	}

	@Override
	public UInfo getUserNames(int user_id) {
		return userDao.getUserDList(user_id);
	}

	@Override
	public UInfo getUserIcons(int user_id) {
		return userDao.getUserIcon(user_id);
	}

	

	@Override
	public List<String> getUserName(List<Integer> list) {
		return userDao.getUserName(list);
	}

	@Override
	public UInfo getUserInfoByTel(String tel) {

		return userDao.getUserInfoByTel(tel);
	}

	@Override
	public String getName(int _user_id) {
		return userDao.getName(_user_id);
	}

	@Override
	public List<UInfo> getUserInfoByStatus(int single_id, List<Integer> list) {

		return userDao.getUserInfoByStatus(single_id, list);
	}

	@Override
	public List<UInfo> getUserInfoBySys(int single_id, List<Integer> list) {

		return userDao.getUserInfoBySys(single_id, list);
	}

	@Override
	public List<UInfo> getUserInfoByName(int single_id, List<Integer> list) {

		return userDao.getUserInfoByName(single_id, list);
	}

	@Override
	public List<UInfo> getUserInfoWithoutSort(List<Integer> list) {

		return userDao.getUserInfoWithoutSort(list);
		
	}
}

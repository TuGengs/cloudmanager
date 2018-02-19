package com.halfmoon.cloudmanager.response.check;

import com.halfmoon.cloudmanager.response.user.UserInfo;

public class SignInRecord {

	private int id;
	private byte is_here;
	private int signed_times;
	private UserInfo user;

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getIs_here() {
		return is_here;
	}

	public void setIs_here(byte is_here) {
		this.is_here = is_here;
	}

	public int getSigned_times() {
		return signed_times;
	}

	public void setSigned_times(int signed_times) {
		this.signed_times = signed_times;
	}

}

package com.halfmoon.cloudmanager.model.check.dto;

public class ShareInfo {
	private String account;
	private String password;
	private int is_open;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIs_open() {
		return is_open;
	}
	public void setIs_open(int is_open) {
		this.is_open = is_open;
	}
	@Override
	public String toString() {
		return "ShareInfo [account=" + account + ", password=" + password
				+ ", is_open=" + is_open + "]";
	}
	
	
}

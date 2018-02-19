package com.halfmoon.cloudmanager.model.check;
/**
 * 分享表的实体
 * @author xiaogao.XU
 *
 */
public class Share {
	
	private int id;
	private int sign_in_check_id;
	private String account;
	private String password;
	// 检查类型是签到型还是扣分型
	private int check_type;
	private int is_open;
	
	public Share() {
		super();
	}
	public Share(int sign_in_check_id, String account, String password,
			int check_type) {
		super();
		this.sign_in_check_id = sign_in_check_id;
		this.account = account;
		this.password = password;
		this.check_type = check_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSign_in_check_id() {
		return sign_in_check_id;
	}
	public void setSign_in_check_id(int sign_in_check_id) {
		this.sign_in_check_id = sign_in_check_id;
	}
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
	
	
	public int getCheck_type() {
		return check_type;
	}
	public void setCheck_type(int check_type) {
		this.check_type = check_type;
	}
	
	public int getIs_open() {
		return is_open;
	}
	public void setIs_open(int is_open) {
		this.is_open = is_open;
	}
	@Override
	public String toString() {
		return "Share [id=" + id + ", sign_in_check_id=" + sign_in_check_id
				+ ", account=" + account + ", password=" + password
				+ ", check_type=" + check_type + ", is_open=" + is_open + "]";
	}
	
	
}

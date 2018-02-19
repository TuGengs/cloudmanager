package com.halfmoon.cloudmanager.model.user;

/**
 * 
 * 登录验证时不要用这个User类，不需要用到这么多属性，
 * 尤其是密码，使用LoggedUser即可
 * 
 * @author hzq
 * @see com.halfmoon.cloudmanager.response.user.LoggedUser
 * 
 */
public class User {

	private int id;
	private String tel;
	private String password;
	private String name;
	private byte gender;
	private byte is_teacher; 		// 是不是老师
	private String clazz; 			// 班级
	private String sysnum;
	private transient int school_id;
	private String academy;
	private String building;
	private String room;
	private transient int icon_id;
	private transient int photo_id;
	private transient String openid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getGender() {
		return gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public byte getIs_teacher() {
		return is_teacher;
	}

	public void setIs_teacher(byte is_teacher) {
		this.is_teacher = is_teacher;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getSysnum() {
		return sysnum;
	}

	public void setSysnum(String sysnum) {
		this.sysnum = sysnum;
	}

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getIcon_id() {
		return icon_id;
	}

	public void setIcon_id(int icon_id) {
		this.icon_id = icon_id;
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", tel=" + tel + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ ", is_teacher=" + is_teacher + ", clazz=" + clazz + ", sysnum=" + sysnum + ", school_id=" + school_id
				+ ", academy=" + academy + ", building=" + building + ", room=" + room + ", icon_id=" + icon_id
				+ ", photo_id=" + photo_id + ", openid=" + openid + "]";
	}

}

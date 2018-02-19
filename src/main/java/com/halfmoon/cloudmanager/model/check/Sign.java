package com.halfmoon.cloudmanager.model.check;

import java.util.Date;

public class Sign {
	
	private int id;
	private int sign_in_check_auto_id;
	private int single_sign_check_id;	
	private int sign_in_check_id;
	private int signed_times;
	private int is_open;
	private int is_face;
	private String name_list;
	private double longitude;
	private double latitude;
	
	private Date open_time;
	
	public Sign(){}
	
	public Sign(int sign_in_check_auto_id, int single_sign_check_id,
			int sign_in_check_id, int signed_times, int is_open,
			double longitude, double latitude) {
		super();
		this.sign_in_check_auto_id = sign_in_check_auto_id;
		this.single_sign_check_id = single_sign_check_id;
		this.sign_in_check_id = sign_in_check_id;
		this.signed_times = signed_times;
		this.is_open = is_open;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSign_in_check_auto_id() {
		return sign_in_check_auto_id;
	}
	public void setSign_in_check_auto_id(int sign_in_check_auto_id) {
		this.sign_in_check_auto_id = sign_in_check_auto_id;
	}
	public int getSingle_sign_check_id() {
		return single_sign_check_id;
	}
	public void setSingle_sign_check_id(int single_sign_check_id) {
		this.single_sign_check_id = single_sign_check_id;
	}
	public int getSign_in_check_id() {
		return sign_in_check_id;
	}
	public void setSign_in_check_id(int sign_in_check_id) {
		this.sign_in_check_id = sign_in_check_id;
	}
	public int getSigned_times() {
		return signed_times;
	}
	public void setSigned_times(int signed_times) {
		this.signed_times = signed_times;
	}
	public int getIs_open() {
		return is_open;
	}
	public void setIs_open(int is_open) {
		this.is_open = is_open;
	}
	public int getIs_face() {
		return is_face;
	}
	public void setIs_face(int is_face) {
		this.is_face = is_face;
	}
	public String getName_list() {
		return name_list;
	}
	public void setName_list(String name_list) {
		this.name_list = name_list;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Date getOpen_time() {
		return open_time;
	}

	public void setOpen_time(Date open_time) {
		this.open_time = open_time;
	}

	@Override
	public String toString() {
		return "Sign [id=" + id + ", sign_in_check_auto_id="
				+ sign_in_check_auto_id + ", single_sign_check_id="
				+ single_sign_check_id + ", sign_in_check_id="
				+ sign_in_check_id + ", signed_times=" + signed_times
				+ ", is_open=" + is_open + ", is_face=" + is_face
				+ ", name_list=" + name_list + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", open_time=" + open_time + "]";
	}

	
	
}

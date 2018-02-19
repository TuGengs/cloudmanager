package com.halfmoon.cloudmanager.model.school;

public class School {

	private int id;
	private String name;
	private transient double longtitude;
	private transient double latitude;
	private byte crawled;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public byte getCrawled() {
		return crawled;
	}

	public void setCrawled(byte crawled) {
		this.crawled = crawled;
	}

}

package com.example.googleplay_10_25.bean;

import java.util.ArrayList;

public class Home {
	private ArrayList<String> picture;//头部的大图url
	private ArrayList<AppInfo> list;//app列表的数据
	public ArrayList<String> getPicture() {
		return picture;
	}
	public void setPicture(ArrayList<String> picture) {
		this.picture = picture;
	}
	public ArrayList<AppInfo> getList() {
		return list;
	}
	public void setList(ArrayList<AppInfo> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Home [picture=" + picture.size() + ", list=" + list.size() + "]";
	}
	
	
	
}

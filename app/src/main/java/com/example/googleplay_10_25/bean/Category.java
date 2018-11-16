package com.example.googleplay_10_25.bean;

import java.util.ArrayList;

public class Category {
	private String title;//分类的标题
	private ArrayList<CategoryInfo> infos;//小的分类
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<CategoryInfo> getInfos() {
		return infos;
	}
	public void setInfos(ArrayList<CategoryInfo> infos) {
		this.infos = infos;
	}
	
	
}

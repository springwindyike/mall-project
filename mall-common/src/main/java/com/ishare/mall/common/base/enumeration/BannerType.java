package com.ishare.mall.common.base.enumeration;
/**
 * Created by YinLin on 2015/11/2.
 * Description : 列表类型
 * Version 1.0
 */
public enum BannerType {
	

	NAV_CHANNEL("导航栏目"),TOPIC_LIST("文章列表栏目"),

	TOPIC_CONTENT("文章内容栏目"),TOPIC_IMG("图片列表栏目");
	
	private String name;
	
	private BannerType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

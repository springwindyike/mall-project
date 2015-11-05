package com.ishare.mall.common.base.dto.cms;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.enumeration.BannerType;
/**
 *  系统栏目
 * @author zhangzhaoxin
 *
 */
public class BannerDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//栏目主键
	private int id;
	//栏目名称
	private String name;
	//栏目是否是自定义链接，0表示否，1表示是
	private int customLink;
	//自定义链接的地址
	private String customLinkUrl;
	// 栏目的类型，枚举类型，该枚举中存在一个name属性用来标识中文的名称
	  private BannerType type;
	  //是否是首页栏目，0表示否，1表示是
	  private int isIndex;
	  //是否是首页的顶部导航栏目，0表示否，1表示是
	  private int isTopNav;
	  //是否是推荐栏目，0表示否，1表示是
	  private int recommend;
	  // 栏目的状态，0表示启用，1表示停止
	  private int status;
	  //栏目的序号
	  private int orders;
	  //父类栏目
	  private BannerDTO  parent;
	  //导航的序号
	  private int navOrder;
	  private int offset;

		private int limit;
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
	public int getCustomLink() {
		return customLink;
	}
	public void setCustomLink(int customLink) {
		this.customLink = customLink;
	}
	public String getCustomLinkUrl() {
		return customLinkUrl;
	}
	public void setCustomLinkUrl(String customLinkUrl) {
		this.customLinkUrl = customLinkUrl;
	}
	public BannerType getType() {
		return type;
	}
	public void setType(BannerType type) {
		this.type = type;
	}
	public int getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}
	public int getIsTopNav() {
		return isTopNav;
	}
	public void setIsTopNav(int isTopNav) {
		this.isTopNav = isTopNav;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public BannerDTO getParent() {
		return parent;
	}
	public void setParent(BannerDTO parent) {
		this.parent = parent;
	}
	public int getNavOrder() {
		return navOrder;
	}
	public void setNavOrder(int navOrder) {
		this.navOrder = navOrder;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	

}

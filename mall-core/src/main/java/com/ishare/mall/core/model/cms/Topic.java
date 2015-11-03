package com.ishare.mall.core.model.cms;

import com.ishare.mall.core.model.manage.ManageUser;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by YinLin on 2015/11/2.
 * Description : 文章表
 * Version 1.0
 */
@Entity
@Table(name="t_topic")
public class Topic {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="title")
	private String title;
	/**
	 * 关键字:通过|来分割不同的关键字
	 */
	@Column(name="key_word")
	private String keyword;
	/**
	 * 文章的状态，默认为0表示未发表，1表示已发布
	 */
	@Column(name="status")
	private int status;
	/**
	 * 是否是推荐文章,0表示不推荐，1表示推荐
	 */
	@Column(name="recommend")
	private int recommend;
	/**
	 * 文章的内容
	 */
	@Column(name="content")
	private String content;
	/**
	 * 文章的摘要
	 */
	@Column(name="summary")
	private String summary;
	/**
	 * 栏目图片id，如果该栏目是图片类型的栏目，就会显示这个id的图片
	 */
	@Column(name="channel_pic_id")
	private int channelPicId;
	/**
	 * 文章的发布时间，用来进行排序的
	 */
	@Column(name="publish_date")
	private Date publishDate;
	/**
	 * 文章的创建时间
	 */
	@Column(name="create_date")
	private Date createDate;
	/**
	 * 文章的作者名称，用来显示用户的昵称，冗余字段
	 */
	@Column(name="author")
	private String author;
	/**
	 * 栏目名称冗余字段
	 */
	@Column(name="banner_name")
	private String bannerName;
	
	/**
	 * 文章所在的频道，多对一
	 */
	@ManyToOne(cascade= CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "banner_id")
	private Banner banner;
	/**
	 * 文章的发布者
	 */
	@ManyToOne(cascade= CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "manage_user_id")
	private ManageUser user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
		
	public Banner getBanner() {
		return banner;
	}

	public void setBanner(Banner banner) {
		this.banner = banner;
	}

	public ManageUser getUser() {
		return user;
	}

	public void setUser(ManageUser user) {
		this.user = user;
	}

	public int getChannelPicId() {
		return channelPicId;
	}
	public void setChannelPicId(int channelPicId) {
		this.channelPicId = channelPicId;
	}
	
	
	public String getBannerName() {
		return bannerName;
	}
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", title=" + title + "]";
	}
	
	public Topic(int id, String title,String keyword, int status,
			int recommend, Date publishDate, String author, String bannerName) {
		super();
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.status = status;
		this.recommend = recommend;
		this.publishDate = publishDate;
		this.author = author;
		this.bannerName = bannerName;
	}
	
	public Topic(int id, String title,String keyword, int status,
			int recommend, Date publishDate, String author, String bannerName,String summary) {
		super();
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.status = status;
		this.recommend = recommend;
		this.publishDate = publishDate;
		this.author = author;
		this.bannerName = bannerName;
		this.summary = summary;
	}
	
	public Topic(int id, String title, String keyword, int status,
			int recommend, String author) {
		super();
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.status = status;
		this.recommend = recommend;
		this.author = author;
	}
	
	public Topic() {
	}
	
}

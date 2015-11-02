package com.ishare.mall.core.model.cms;

import com.ishare.mall.common.base.enumeration.BannerType;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by YinLin on 2015/11/2.
 * Description : 系统栏目
 * Version 1.0
 */
@Entity
@Table(name="t_banner")
public class Banner {

    public static final String ROOT_NAME = "系统栏目";

    public static final int ROOT_ID = 0;
    /**
     * 栏目的主键
     */
    @Id
    @GeneratedValue
    private int id;
    /**
     *栏目的名称
     */
    @NotEmpty(message="栏目名称不能为空")
    private String name;
    /**
     * 栏目是否是自定义链接，0表示否，1表示是
     */
    @Column(name="custom_link")
    private int customLink;
    /**
     * 自定义链接的地址
     */
    @Column(name="custom_link_url")
    private String customLinkUrl;
    /**
     * 栏目的类型，枚举类型，该枚举中存在一个name属性用来标识中文的名称
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 13, nullable = false)
    private BannerType type;
    /**
     * 是否是首页栏目，0表示否，1表示是
     */
    @Column(name="is_index")
    private int isIndex;
    /**
     * 是否是首页的顶部导航栏目，0表示否，1表示是
     */
    @Column(name="is_top_nav")
    private int isTopNav;
    /**
     * 是否是推荐栏目，0表示否，1表示是
     */
    @Column(name="recommend")
    private int recommend;
    /**
     * 栏目的状态，0表示启用，1表示停止
     */
    @Column(name="status")
    private int status;
    /**
     * 栏目的序号
     */
    @Column(name="orders")
    private int orders;
    /**
     * 父类栏目
     */
    @ManyToOne(cascade= CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Banner parent;
    /**
     * 导航的序号
     */
    @Column(name="navOrder")
    private int navOrder;


    public int getNavOrder() {
        return navOrder;
    }
    public void setNavOrder(int navOrder) {
        this.navOrder = navOrder;
    }
    @ManyToOne(cascade= CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name="pid")
    public Banner getParent() {
        return parent;
    }
    public void setParent(Banner parent) {
        this.parent = parent;
    }
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

    public Banner() {
    }
    public Banner(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public Banner(int id, String name, BannerType ct) {
        super();
        this.id = id;
        this.name = name;
        this.type = ct;
    }
    public Banner(int id, String name, int customLink, String customLinkUrl) {
        super();
        this.id = id;
        this.name = name;
        this.customLink = customLink;
        this.customLinkUrl = customLinkUrl;
    }
}

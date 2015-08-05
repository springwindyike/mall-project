package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;
import com.google.common.collect.Lists;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by dongqi on 15/7/12.
 * <p>
 * 商品
 */
@Entity
@Table(name = "es_product")
public class Product extends AbstractEntity {

    @Column(name = "product_name")
    private String name;
    @Column(name = "product_desc")
    private String description;
    private Price price;//价格

    private String code;//货号
    @Column(name = "product_level")
    private String level;//等级
    private String color;//色号
    @Column(name = "product_spec")
    private String specification;// 规格
    private String unit;//单位
    private String weight;//重量
    private Date createTime;//生产日期
    private Long stock = 0L;//库存

    @Type(type = "yes_no")
    @Column(name = "is_show")
    private Boolean show = Boolean.FALSE;//是否上架

    @Type(type = "yes_no")
    @Column(name = "is_competing")
    private Boolean competing = Boolean.FALSE;//竞品

    @Type(type = "yes_no")
    @Column(name = "is_limit")
    private Boolean limit = Boolean.FALSE;//限购

    @Type(type = "yes_no")
    @Column(name = "is_promotion")
    private Boolean promotion = Boolean.FALSE;//促销

    @Type(type = "yes_no")
    @Column(name = "is_best")
    private Boolean best = Boolean.FALSE;//精品

    @Type(type = "yes_no")
    @Column(name = "is_newest")
    private Boolean newest = Boolean.TRUE;//新品

    @Type(type = "yes_no")
    @Column(name = "is_hot")
    private Boolean hot = Boolean.FALSE;//热销

    @Type(type = "yes_no")
    @Column(name = "is_suit")
    private Boolean suit = Boolean.FALSE;//套餐

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    private Brand brand;//品牌

    @OneToMany
    @JoinTable(name = "es_products_categories", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories = Lists.newArrayList();//类目

    @OneToMany(mappedBy = "product")
    private List<Image> gallery = Lists.newArrayList();//图片

    public List<Image> getGallery() {
        return gallery;
    }

    public void setGallery(List<Image> gallery) {
        this.gallery = gallery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean getCompeting() {
        return competing;
    }

    public void setCompeting(Boolean competing) {
        this.competing = competing;
    }

    public Boolean getLimit() {
        return limit;
    }

    public void setLimit(Boolean limit) {
        this.limit = limit;
    }

    public Boolean getPromotion() {
        return promotion;
    }

    public void setPromotion(Boolean promotion) {
        this.promotion = promotion;
    }

    public Boolean getBest() {
        return best;
    }

    public void setBest(Boolean best) {
        this.best = best;
    }

    public Boolean getNewest() {
        return newest;
    }

    public void setNewest(Boolean newest) {
        this.newest = newest;
    }

    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    public Boolean getSuit() {
        return suit;
    }

    public void setSuit(Boolean suit) {
        this.suit = suit;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

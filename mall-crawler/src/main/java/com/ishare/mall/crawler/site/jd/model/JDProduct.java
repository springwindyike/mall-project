package com.ishare.mall.crawler.site.jd.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dongqi on 15/9/7.
 */
@Entity
@Table(name = "spider_jd_product")
public class JDProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String link;
    private String name;
    private String code;//skuid 唯一值

    @Type(type = "yes_no")
    @Column(name = "is_self")
    private boolean self;

    @ElementCollection
    @CollectionTable(name = "spider_jd_product_attr", joinColumns = @JoinColumn(name = "id"))
    private Map<String, String> attributes = Maps.newHashMap();
    @ElementCollection
    @CollectionTable(name = "spider_jd_intro_image", joinColumns = @JoinColumn(name = "id"))
    private List<String> introImgs = Lists.newArrayList();
    @ElementCollection
    @CollectionTable(name = "spider_jd_photo", joinColumns = @JoinColumn(name = "id"))
    private List<String> photo = Lists.newArrayList();
    private double price;
    private double priceOrigin;
    private String stock;
    private String tag;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column(name = "jd_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jdDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<String> getIntroImgs() {
        return introImgs;
    }

    public void setIntroImgs(List<String> introImgs) {
        this.introImgs = introImgs;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceOrigin() {
        return priceOrigin;
    }

    public void setPriceOrigin(double priceOrigin) {
        this.priceOrigin = priceOrigin;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getJdDatetime() {
        return jdDatetime;
    }

    public void setJdDatetime(Date jdDatetime) {
        this.jdDatetime = jdDatetime;
    }

    @Override
    public String toString() {
        return "JDProduct{" +
                "id=" + id +
                ", code=" + code +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JDProduct product = (JDProduct) o;

        return link.equals(product.link);

    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }
}

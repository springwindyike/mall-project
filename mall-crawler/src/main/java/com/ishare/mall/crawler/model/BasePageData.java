package com.ishare.mall.crawler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "spider_base_page_data")
public class BasePageData {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private FetchUrl fetchUrl;

    private String code;
    private String name;
    @Column(length = 1024)
    private String link;
    private String priceText;
    private String priceOriginText;
    private String stock;
    private String tag;
    private String datetimeText;//上架时间

    @Type(type = "yes_no")
    @Column(name = "is_self")
    private boolean self;//是否自营

    private String thirdPartyShopName;//第三方店铺名称

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ElementCollection
    @CollectionTable(name = "spider_base_page_data_attr", joinColumns = @JoinColumn(name = "id"))
    private Map<String, String> attributes = Maps.newHashMap();
    @ElementCollection
    @CollectionTable(name = "spider_base_page_data_intro_image", joinColumns = @JoinColumn(name = "id"))
    private List<String> introImages = Lists.newArrayList();
    @ElementCollection
    @CollectionTable(name = "spider_base_page_data_photo", joinColumns = @JoinColumn(name = "id"))
    private List<String> photos = Lists.newArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FetchUrl getFetchUrl() {
        return fetchUrl;
    }

    public void setFetchUrl(FetchUrl fetchUrl) {
        this.fetchUrl = fetchUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public String getPriceOriginText() {
        return priceOriginText;
    }

    public void setPriceOriginText(String priceOriginText) {
        this.priceOriginText = priceOriginText;
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

    public String getDatetimeText() {
        return datetimeText;
    }

    public void setDatetimeText(String datetimeText) {
        this.datetimeText = datetimeText;
    }

    public boolean getSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public String getThirdPartyShopName() {
        return thirdPartyShopName;
    }

    public void setThirdPartyShopName(String thirdPartyShopName) {
        this.thirdPartyShopName = thirdPartyShopName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BasePageData{" +
                "id=" + id +
                ", fetchUrl=" + fetchUrl +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", priceText='" + priceText + '\'' +
                ", priceOriginText='" + priceOriginText + '\'' +
                ", stock='" + stock + '\'' +
                ", tag='" + tag + '\'' +
                ", datetimeText='" + datetimeText + '\'' +
                ", self=" + self +
                ", thirdPartyShopName='" + thirdPartyShopName + '\'' +
                ", updateTime=" + updateTime +
                ", attributes=" + attributes +
                ", introImages=" + introImages +
                ", photos=" + photos +
                '}';
    }
}

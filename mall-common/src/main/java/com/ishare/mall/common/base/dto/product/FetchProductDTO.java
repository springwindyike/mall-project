package com.ishare.mall.common.base.dto.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ishare.mall.common.base.dto.generic.GenericDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by YinLin on 2015/10/10.
 * Description :
 * Version 1.0
 */
public class FetchProductDTO extends GenericDTO {
    //��ƷCODE
    private String code;
    //��Ʒ����
    private String name;
    //����������
    private String link;
    //��Ʒ���ۼ۸�
    private String priceText;
    //��Ʒԭ��
    private String priceOriginText;
    //��� Y N
    private String stock;
    //��Ʒ��ǩ
    private String tag;
    //��Ʒ�ϼ�ʱ��
    private String datetimeText;//�ϼ�ʱ��

    private boolean self;//�Ƿ���Ӫ

    private String thirdPartyShopName;//��������������

    private Date updateTime;

    private Map<String, String> attributes = Maps.newHashMap();

    private List<String> introImages = Lists.newArrayList();

    private List<String> photos = Lists.newArrayList();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public boolean isSelf() {
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
}

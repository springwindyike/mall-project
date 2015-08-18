package com.ishare.mall.common.base.dto.product;

import com.ishare.mall.common.base.object.BaseObject;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.Date;

/**
 * Created by YinLin on 2015/8/7.
 * Description: productDTO
 * Version 1.0
 */
@JsonAutoDetect
public class ProductDetailDTO implements BaseObject {
    private Integer id;
    //货号
    private String code;
    //物品名字
    private String name;
    //物品类型 便于search 10010010001
    private String typeCode;
    //物品
    private String model;
    //商品进价
    private Float basePrice;
    //市场价格
    private Float marketPrice;
    //卖出价格
    private Float sellPrice;
    //商品重量 单位（g）
    private Integer weight;
    //描述
    private String description;
    //购买须知
    private String buyExplain;
    //是否可见
    private Boolean visible = true;
    //点击人气
    private Integer clickCount = 1;
    //卖出人气
    private Integer sellCount = 0;
    //是否推荐
    private Boolean commend = Boolean.FALSE;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //创建者
    private String createByAccount;
    //更新者
    private String updateByAccount;

    private Integer brandId;//品牌

    private String brandName;//品牌名字

    private Integer channelId;

    private String channelName;//渠道名字

    private Integer typeId;

    private String typeName;//商品类型名字
    //默认样式图片
    private String defaultImageUrl;
    //库存
    private Integer inventory; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public Float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuyExplain() {
        return buyExplain;
    }

    public void setBuyExplain(String buyExplain) {
        this.buyExplain = buyExplain;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public Boolean getCommend() {
        return commend;
    }

    public void setCommend(Boolean commend) {
        this.commend = commend;
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

    public String getCreateByAccount() {
        return createByAccount;
    }

    public void setCreateByAccount(String createByAccount) {
        this.createByAccount = createByAccount;
    }

    public String getUpdateByAccount() {
        return updateByAccount;
    }

    public void setUpdateByAccount(String updateByAccount) {
        this.updateByAccount = updateByAccount;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
    
}

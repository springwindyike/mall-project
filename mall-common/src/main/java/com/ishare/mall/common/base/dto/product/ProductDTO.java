package com.ishare.mall.common.base.dto.product;

import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.object.BaseObject;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YinLin on 2015/8/7.
 * Description: productDTO
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class ProductDTO implements BaseObject {
    
	private Integer id;
	
    //物品名字
    private String name;
    
    //商品进价
    private Float basePrice;
    
    //市场价格
    private Float marketPrice;
    
    //卖出价格
    private Float sellPrice;
  
    //描述
    private String description;
    
    //购买须知
    private String buyExplain;
    
    //是否可见
    private Boolean visible = true;
    
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
    
    private int offset;
    
    private int limit;

    private PageDTO pageDTO;

    //默认样式图片
    private String defaultImageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }

    
	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
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

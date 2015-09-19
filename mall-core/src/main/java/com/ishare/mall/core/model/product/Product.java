package com.ishare.mall.core.model.product;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.information.Origin;
import com.ishare.mall.core.model.member.Member;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_NAME;

/**
 * Created by YinLin on 2015/7/30.
 * Description: 商品信息
 * Version 1.0
 */
@Table(name = TABLE_PRODUCT_NAME)
@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //货号
    @Column(name = "product_code",length = 15)
    private String code;
    //物品名字
    @Column(name = "product_name",length = 27)
    private String name;
    //物品类型 便于search 10010010001
    @Column(name = "product_type_code",length = 15)
    private String typeCode;
    //物品
    @Column(name = "product_model",length = 25)
    private String model;
    //商品进价
    @Column(name = "product_base_price",length = 8)
    private Float basePrice;
    //市场价格
    @Column(name = "product_market_price",length = 8)
    private Float marketPrice;
    //卖出价格
    @Column(name = "product_sell_price",length = 8)
    private Float sellPrice;
    //商品重量 单位（g）
    @Column(name = "product_weight",length = 6)
    private Integer weight;
    //描述
    @Column(name = "product_description",length = 150)
    private String description;
    //购买须知
    @Column(name = "product_buy_explain",length = 150)
    private String buyExplain;
    //是否可见
    @Column(name = "product_visible",length = 5)
    private Boolean visible = true;
    //点击人气
    @Column(name = "product_click_count",length = 7)
    private Integer clickCount = 1;
    //卖出人气
    @Column(name = "product_sell_count",length = 7)
    private Integer sellCount = 0;
    //是否推荐
    @Column(name = "product_commend",length = 5)
    private Boolean commend = Boolean.FALSE;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_create_time",length = 20)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_update_time",length = 20)
    private Date updateTime;
    //默认的图片地址 冗余
    @Column(name = "default_img_url",length = 200)
    private String defaultImageUrl;
    //库存
    @Column(name = "product_inventory",length = 6)
    private Integer inventory;
    @JsonIgnore
    //创建者
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "product_create_by")
    private Member createBy;
    //更新者
    @JsonIgnore
    @ManyToOne(cascade= CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "product_update_by")
    private Member updateBy;

    @JsonIgnore
    @ManyToOne(cascade= CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "product_brand_id")
    private Brand brand;//品牌

    @JsonIgnore
    @ManyToOne(cascade= CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "product_channel_id")
    private Channel channel;

    @JsonIgnore
    @ManyToOne(cascade= CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "product_type_id")
    private ProductType type;
    //是否自营
    @Column(name = "is_self")
    private Boolean self;
    //第三方外键
    @Column(name = "origin_id")
    private Origin origin;
    //第三方link
    @Column(name = "origin_link")
    private String link;
    //第三方Tag
    @Column(name = "origin_tag")
    private String tag;

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

    public Member getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Member createBy) {
        this.createBy = createBy;
    }

    public Member getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Member updateBy) {
        this.updateBy = updateBy;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
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

    public Boolean getSelf() {
        return self;
    }

    public void setSelf(Boolean self) {
        this.self = self;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}

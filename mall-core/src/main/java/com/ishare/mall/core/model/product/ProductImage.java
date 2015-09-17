package com.ishare.mall.core.model.product;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.member.Member;

import javax.persistence.*;
import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_STYLE_IMAGE_NAME;

/**
 * Created by YinLin on 2015/7/31.
 * Description : 产品图片类
 * Version 1.0
 */
@Entity(name = TABLE_PRODUCT_STYLE_IMAGE_NAME)
public class ProductImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**所属产品**/
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "product_style_id")
    private ProductStyle productStyle;
    /**图片地址**/
    @Column(length = 200, nullable = false, name = "product_image_url")
    private String url;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_create_time",length = 20)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_update_time",length = 20)
    private Date updateTime;
    //创建者
    @ManyToOne(cascade= CascadeType.REFRESH, optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_create_by")
    private Member createBy;
    //更新者
    @ManyToOne(cascade= CascadeType.REFRESH, optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_update_by")
    private Member updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductStyle getProductStyle() {
        return productStyle;
    }

    public void setProductStyle(ProductStyle productStyle) {
        this.productStyle = productStyle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}

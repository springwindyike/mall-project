package com.ishare.mall.core.model.product;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.member.Member;

import javax.persistence.*;
import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_STYLE_NAME;

/**
 * Created by YinLin on 2015/7/31.
 * Description:样式类
 * Version 1.0
 */
@Entity
@Table(name = TABLE_PRODUCT_STYLE_NAME)
public class ProductStyle extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    /**样式名称**/
    @Column(length = 32, nullable = false, name = "product_style_name")
    private String name;
    /**图片地址**/
    @Column(length=200,nullable=false, name = "product_image_url")
    private String imageUrl;
    /**是否可见**/
    @Column(nullable = false, name = "product_style_visible",length = 5)
    private Boolean visible = Boolean.TRUE;
    /**所属产品**/
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_create_time",length = 20)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_update_time",length = 20)
    private Date updateTime;
    //创建者
    @ManyToOne(cascade= CascadeType.REFRESH, optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_create_by")
    private Member createBy;
    //更新者
    @ManyToOne(cascade= CascadeType.REFRESH, optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_update_by")
    private Member updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public ProductStyle() {

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductStyle other = (ProductStyle) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public ProductStyle(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public ProductStyle(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

package com.ishare.mall.common.base.dto.product;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Administrator on 2015/9/16.
 */
@XmlRootElement
@JsonAutoDetect
public class ProductStyleDTO extends GenericDTO {
    private Long id;
    /**样式名称**/
    private String name;
    /**图片地址**/
    private String imageUrl;
    /**是否可见**/
    private Boolean visible = Boolean.TRUE;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //更新者


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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}

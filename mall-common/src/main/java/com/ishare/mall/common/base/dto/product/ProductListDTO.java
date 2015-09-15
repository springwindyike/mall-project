package com.ishare.mall.common.base.dto.product;

import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.object.BaseObject;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by YinLin on 2015/8/7.
 * Description: 商品列表API DTO 只要是查询或者List全用这个DTO
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class ProductListDTO implements BaseObject {
    //商品ID
    private Integer id;
    //商品名字
    private String name;
    //商品价格
    private Float sellPrice;
    //默认样式图片
    private String defaultImageUrl;
    private Integer limit;
    private Integer offset;
    private PageDTO pageDTO;
    private Map<String,Object> map;
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

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public PageDTO getPageDTO() {
        return pageDTO;
    }

    public void setPageDTO(PageDTO pageDTO) {
        this.pageDTO = pageDTO;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}

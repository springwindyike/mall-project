package com.ishare.mall.common.base.dto.product;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
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
public class ProductDTO extends GenericDTO {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer id;
	

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

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
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

	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}

	public String getDefaultImageUrl() {
		return defaultImageUrl;
	}

	public void setDefaultImageUrl(String defaultImageUrl) {
		this.defaultImageUrl = defaultImageUrl;
	}

  
 
}

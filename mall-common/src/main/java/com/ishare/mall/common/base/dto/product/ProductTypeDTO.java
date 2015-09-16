package com.ishare.mall.common.base.dto.product;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.ishare.mall.common.base.dto.page.PageDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.object.BaseObject;

/**
 * Created by liaochenglei on 2015/9/9.
 * Description: productDTO
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class ProductTypeDTO implements BaseObject {
	
	private Integer id;

	private Integer parentId;
	
	private String typeName;

	private List <ProductTypeDTO> child; 
	
	private String code;

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

	public Integer getParentId() {
		return parentId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<ProductTypeDTO> getChild() {
		return child;
	}

	public void setChild(List<ProductTypeDTO> child) {
		this.child = child;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
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

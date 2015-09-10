package com.ishare.mall.common.base.dto.product;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

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
}

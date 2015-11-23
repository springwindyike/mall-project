package com.ishare.mall.common.base.dto.attribute;

import com.ishare.mall.common.base.dto.generic.GenericDTO;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

/**
 * Created by liaochenglei on 2015/10/26.
 * Description: productDTO
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class AttributeDTO extends GenericDTO {
   
	private static final long serialVersionUID = 1L;
 
	//属性的id
	private Integer id;
	//产品分类的id
	private Integer productTypeId;
	
	//属性规格的名字
	private String name;
	
	//属性规格所属的组的id
	private Integer attributeGroupId;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAttributeGroupId() {
		return attributeGroupId;
	}

	public void setAttributeGroupId(Integer attributeGroupId) {
		this.attributeGroupId = attributeGroupId;
	}
	
}

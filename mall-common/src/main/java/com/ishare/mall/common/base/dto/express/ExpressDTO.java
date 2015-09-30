package com.ishare.mall.common.base.dto.express;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.dto.generic.GenericDTO;


/**
 * Created by liaochenglei on 2015/9/29.
 * Description: ExpressDTO
 */
@XmlRootElement
@JsonAutoDetect
public class ExpressDTO extends GenericDTO {

	private static final long serialVersionUID = 1L;
	
	private String id;

	private String order;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	

}

package com.ishare.mall.common.base.dto.order;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ZhangZhaoxin on 2015/9/25.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class OrderResultDTO extends GenericDTO {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private int code;

    private String message;
    
    private OrderDetailDTO orderDetailDTO;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public OrderDetailDTO getOrderDetailDTO() {
		return orderDetailDTO;
	}

	public void setOrderDetailDTO(OrderDetailDTO orderDetailDTO) {
		this.orderDetailDTO = orderDetailDTO;
	}

}

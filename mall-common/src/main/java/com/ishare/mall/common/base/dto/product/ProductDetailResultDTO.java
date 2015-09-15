package com.ishare.mall.common.base.dto.product;

import com.ishare.mall.common.base.dto.member.MemberLoginDTO;
import com.ishare.mall.common.base.object.BaseObject;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liaochenglei on 2015/8/7.
 * Description: productDTO
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class ProductDetailResultDTO implements BaseObject {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private int code;

    private String message;

    private ProductDetailDTO productDetailDTO;

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

	public ProductDetailDTO getProductDetailDTO() {
		return productDetailDTO;
	}

	public void setProductDetailDTO(ProductDetailDTO productDetailDTO) {
		this.productDetailDTO = productDetailDTO;
	}


}

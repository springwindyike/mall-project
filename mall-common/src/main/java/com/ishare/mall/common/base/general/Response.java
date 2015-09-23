package com.ishare.mall.common.base.general;

import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.dto.test.TestDTO;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import java.io.Serializable;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.PROPERTY)
@XmlSeeAlso(
        {
                TestDTO.class,
                ProductDetailDTO.class
        }
)
@JsonAutoDetect
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 3886269150595934287L;

    //错误码
    private Integer code;
    //是否操作成功
    private boolean success = Status.SUCCESS;
    //数据返回数据对象
    private T data;
    //错误消息
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public interface Status {
        int OK = 200;
        boolean SUCCESS = true;
        boolean FAILURE = false;
    }
}

package com.ishare.mall.common.base.dto.error;

import com.ishare.mall.common.base.dto.generic.GenericDTO;

/**
 * Created by YinLin on 2015/8/31.
 * Description :
 * Version 1.0
 */
public class ErrorDTO<T extends Object> extends GenericDTO {

    private Integer code;

    private  T message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}

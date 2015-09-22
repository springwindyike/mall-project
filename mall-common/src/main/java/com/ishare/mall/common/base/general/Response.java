package com.ishare.mall.common.base.general;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */

@XmlRootElement
@JsonAutoDetect
public class Response extends GenericDTO {
    //错误码
    private Integer code;
    //是否操作成功
    private boolean success = Status.SUCCESS;
    //数据返回数据对象
    private Object data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
    }
}

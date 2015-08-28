package com.ishare.mall.common.base.dto.generic;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by YinLin on 2015/8/27.
 * Description : DTO父类
 * Version 1.0
 */
public abstract class GenericDTO implements Serializable {
    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 3886269150595934285L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}

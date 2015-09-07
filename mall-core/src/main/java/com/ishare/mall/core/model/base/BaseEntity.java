package com.ishare.mall.core.model.base;

import com.ishare.mall.common.base.object.BaseObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.MappedSuperclass;

/**
 * Created by YinLin on 2015/7/31.
 * Description:
 * Version 1.0
 */
@MappedSuperclass
public class BaseEntity implements BaseObject {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}

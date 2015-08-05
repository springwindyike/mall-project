package com.ishare.mall.core.model.time;

import com.ishare.mall.common.base.object.BaseObject;

import javax.persistence.Embeddable;

/**
 * Created by YinLin on 2015/8/3.
 * Description: 各类时间
 * Version 1.0
 */
@Embeddable
public class Time implements BaseObject {
    private String createTime;
    private String updateTime;
}

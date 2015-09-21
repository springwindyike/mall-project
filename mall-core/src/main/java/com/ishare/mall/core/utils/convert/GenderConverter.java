package com.ishare.mall.core.utils.convert;

import com.ishare.mall.common.base.enumeration.Gender;
import org.apache.commons.beanutils.Converter;

/**
 * Created by YinLin on 2015/8/26.
 * Description :
 * Version 1.0
 */
public class GenderConverter implements Converter {
    @Override
    public Gender convert(Class clazz, Object value) {
        if(value instanceof Gender) return (Gender)value;
        try {
            return Gender.valueOf((String)value);
        } catch (Exception e) {
            return null;
        }
    }
}

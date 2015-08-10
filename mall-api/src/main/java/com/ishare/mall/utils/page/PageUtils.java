package com.ishare.mall.utils.page;

import com.google.common.collect.Lists;
import com.ishare.mall.common.base.object.BaseObject;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * Created by YinLin on 2015/8/10.
 * Description: 提供page的转换
 * Version 1.0
 */
public class PageUtils {
    public static <T extends BaseObject, E extends BaseObject> Page<E> mapper(Page<T> page, Pageable pageable, Class<E> clazzE) {
        List<T> element = page.getContent();
        List<E> resultElement = Lists.newArrayList();
        if (element != null && element.size() > 0) {
          resultElement = (List<E>) MapperUtils.mapAsList(element, clazzE);
        }
        Page<E> result = new PageImpl<E>(resultElement, pageable, page.getTotalElements());
        return result;
    }


}

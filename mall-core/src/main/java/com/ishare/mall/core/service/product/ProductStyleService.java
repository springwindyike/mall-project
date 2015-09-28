package com.ishare.mall.core.service.product;

import com.ishare.mall.core.model.product.ProductStyle;

import java.util.List;

/**
 * Created by wanghao on 2015/9/16.
 *
 */
public interface ProductStyleService {
    List<ProductStyle> findByProductStyle(Integer id);
}

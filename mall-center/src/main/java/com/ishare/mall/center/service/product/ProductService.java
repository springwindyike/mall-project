package com.ishare.mall.center.service.product;

import com.ishare.mall.common.base.dto.product.ProductDetailDTO;

/**
 * Created by Administrator on 2015/11/12.
 */
public interface ProductService {
    ProductDetailDTO findOne(Integer id);
}

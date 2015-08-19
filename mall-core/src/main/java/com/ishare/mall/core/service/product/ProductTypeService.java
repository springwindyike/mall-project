package com.ishare.mall.core.service.product;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.model.product.ProductType;

/**
 * Created by ZhangZhaoxin on 2015/8/18.
 * Description:
 * Version 1.0
 */
public abstract interface ProductTypeService {

    public abstract Page<ProductType> search(Map<String, Object> searchParams, PageRequest pageRequest);

    public abstract ProductType findOne(Integer id);

}

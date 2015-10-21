package com.ishare.mall.core.service.product;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.exception.ProductServiceException;
import com.ishare.mall.core.exception.ProductTypeServiceException;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.model.product.ProductType;

/**
 * Created by ZhangZhaoxin on 2015/8/18.
 * Description:
 * Version 1.0
 */
public abstract interface ProductTypeService {

    public abstract Page<ProductType> search(Map<String, Object> searchParams, PageRequest pageRequest)throws ProductTypeServiceException;

    public abstract ProductType findOne(Integer id) throws ProductTypeServiceException;

    public abstract List<ProductType> findByLevel(Integer id)throws ProductTypeServiceException;
    
    public abstract List<ProductType>findByParentId(Integer pariendId)throws ProductTypeServiceException;
    /**
     * 保存产品分类
     * @param productType
     */
       void saveProductType(ProductType productType) throws ProductTypeServiceException;
}

package com.ishare.mall.core.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.product.ProductType;

/**
 * Created by ZhangZhaoxin on 2015/8/18.
 * Description:
 * Version 1.0
 */
@SuppressWarnings("rawtypes")
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>, JpaSpecificationExecutor {
	
}

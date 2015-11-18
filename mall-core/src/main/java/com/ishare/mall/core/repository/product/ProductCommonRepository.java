package com.ishare.mall.core.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.product.ProductCommon;

/**
 * Created by liaochenglei on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface ProductCommonRepository extends JpaRepository<ProductCommon, Integer>, JpaSpecificationExecutor {

}

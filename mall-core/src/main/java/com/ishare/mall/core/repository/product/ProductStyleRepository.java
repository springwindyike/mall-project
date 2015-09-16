package com.ishare.mall.core.repository.product;

import com.ishare.mall.core.model.product.ProductStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by YinLin on 2015/9/16.
 * Description : 商品样式
 * Version 1.0
 */
public interface ProductStyleRepository extends JpaRepository<ProductStyle, Long>, JpaSpecificationExecutor {
}

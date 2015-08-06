package com.ishare.mall.core.repository;

import com.ishare.mall.core.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by YinLin on 2015/8/6.
 * Description : 商品表的
 * Version 1.0
 */
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor {

}

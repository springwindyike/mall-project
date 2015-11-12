package com.ishare.mall.core.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.product.ProductReviewCover;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface ProductReviewCoverRepository extends JpaRepository<ProductReviewCover, Integer>, JpaSpecificationExecutor {

}

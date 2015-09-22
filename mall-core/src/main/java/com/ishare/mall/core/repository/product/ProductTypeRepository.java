package com.ishare.mall.core.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ishare.mall.core.model.product.ProductType;

/**
 * Created by ZhangZhaoxin on 2015/8/18.
 * Description:
 * Version 1.0
 */
@SuppressWarnings("rawtypes")
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>, JpaSpecificationExecutor {
	@Query("SELECT o FROM ProductType o WHERE o.level=?1")
    List<ProductType> findByLevel(Integer id);
	
	@Query("SELECT o FROM ProductType o WHERE o.parent.id=?1")
    List<ProductType> findByParendId(Integer parentId);
}



package com.ishare.mall.core.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ishare.mall.core.model.information.Brand;

/**
 * Created by liaochenglei on 2015/10/26.
 * Description:
 * Version 1.0
 */
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor {/*
	
	@Query("SELECT b FROM Brand b")
	Page<Brand> findAllBrand(Pageable pageable);

*/}

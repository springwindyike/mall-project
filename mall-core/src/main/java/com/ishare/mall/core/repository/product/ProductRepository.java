package com.ishare.mall.core.repository.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ishare.mall.core.model.product.Product;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor {
    @Query("SELECT p FROM Product p WHERE p.code=?1 and visible = true and p.fetch.id IS NOT NULL")
    List<Product> findByCode(String code);

	@Query("SELECT p FROM Product p WHERE p.channel.id = ?1")
	Page<Product> findByChannelId(Integer channelId, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.id=?1 and p.channel.id = ?2")
	Page<Product> findBycondition(Integer productId, Integer channelId, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.id=?1")
	Page<Product> findById(Integer productId,Pageable pageable);
	
	  @Query("SELECT p FROM Product p WHERE p.brand.id=?1")
	    List<Product> findByBrandId(Integer brandId);
}

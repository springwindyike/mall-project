package com.ishare.mall.core.repository.product;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.product.Product;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor {
    @Query("SELECT p FROM Product p WHERE p.code=?1")
    List<Product> findByCode(String code);
    
	@Query("SELECT p FROM Product p WHERE p.channel.id = ?1")
	Page<Product> findByChannelId(Integer channelId, Pageable pageable);
	


//@Modifying
//@Query("update Product p set p.code = ?1 and p.name = ?2 and p.typeCode = ?3 and p.basePrice = ?4 and p.marketPrice = ?5 and p.description = ?6 and p.updateTime = ?7 and p.inventory = ?8 where p.id = ?9")
//int updateProduct(String code, String name,String typeCode,float basePrice,float marketPrice,String description,Date updateTime,Integer inventory,Integer id);
}

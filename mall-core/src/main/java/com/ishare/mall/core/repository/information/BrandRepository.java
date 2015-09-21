package com.ishare.mall.core.repository.information;

import java.util.Date;

import com.ishare.mall.core.model.information.Brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor {
	
	@Modifying
	@Query("update Brand b set b.name = ?1 and b.logoUrl = ?2 and b.country = ?3 and b.province = ?4 and b.city = ?5 and b.district = ?6 and b.detail = ?7 where b.id = ?9")
	int updateBrand(String name, String logoUrl,String country,String province,String city,String district,String detail,Integer id);

}

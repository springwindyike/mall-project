package com.ishare.mall.core.repository.information;

import com.ishare.mall.core.model.information.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor {

}

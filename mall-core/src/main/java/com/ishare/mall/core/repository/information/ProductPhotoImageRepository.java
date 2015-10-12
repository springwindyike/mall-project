package com.ishare.mall.core.repository.information;

import com.ishare.mall.core.model.product.ProductPhotoImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface ProductPhotoImageRepository extends JpaRepository<ProductPhotoImage, Integer>, JpaSpecificationExecutor {
	@Query("SELECT o FROM ProductPhotoImage o WHERE o.product.id = ?1")
	List<ProductPhotoImage> findByProductId(Integer id);
}

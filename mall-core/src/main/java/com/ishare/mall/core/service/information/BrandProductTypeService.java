package com.ishare.mall.core.service.information;

import com.ishare.mall.core.exception.BrandProductTypeServiceException;
import com.ishare.mall.core.model.information.ProductTypeBrand;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public abstract interface BrandProductTypeService {    
	/**
	 * 添加品牌分类信息
	 * @param brand
	 */
	void add (ProductTypeBrand productTypeBrand)throws BrandProductTypeServiceException;
}

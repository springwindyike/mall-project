package com.ishare.mall.core.service.information.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.exception.BrandServiceException;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.model.information.ProductTypeBrand;
import com.ishare.mall.core.repository.information.BrandProductTypeRepository;
import com.ishare.mall.core.service.information.BrandProductTypeService;

/**
 * Created by YinLin on 2015/8/10.
 * Description: 商品类别业务逻辑表
 * Version 1.0
 */
@Service
@Transactional
public class BrandProductTypeServiceImpl implements BrandProductTypeService {

	 private static final Logger log = LoggerFactory.getLogger(BrandProductTypeServiceImpl.class);
	    @Autowired
	    private BrandProductTypeRepository brandProductTypeRepository;
	@Override
	public void add(ProductTypeBrand productTypeBrand)
			throws BrandServiceException {

		try {
			brandProductTypeRepository.save(productTypeBrand);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BrandServiceException("品牌分类保存失败");}
	}
}

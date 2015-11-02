package com.ishare.mall.core.service.information.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.exception.BrandServiceException;
import com.ishare.mall.core.exception.ProductServiceException;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.repository.information.BrandRepository;
import com.ishare.mall.core.service.information.BrandService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;

/**
 * Created by YinLin on 2015/8/10.
 * Description: 商品类别业务逻辑表
 * Version 1.0
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand findOne(Integer id) {
        return brandRepository.findOne(id);
    }

    @Override
    public Page<Brand> search(Map<String, Object> searchParams, PageRequest pageRequest) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Brand> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), Brand.class);
        Page<Brand> page = brandRepository.findAll(spec, pageRequest);
        log.debug("filters: {}, total: {}, content: {}", filters, page.getTotalElements(), page.getContent());
        return page;
    }

    public Logger getLog() {
        return log;
    }

	@Override
	public Page<Brand> findAllBrand(PageRequest pageRequest)
			throws BrandServiceException {
		try {
			Page<Brand> page = brandRepository.findAll(null, pageRequest);
			return page;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BrandServiceException("查询品牌失败");
		}
	}

	@Override
	public void delBrand(Integer id) throws BrandServiceException {
		try {
			brandRepository.delete(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BrandServiceException("删除品牌失败");
		}
	}

	@Override
	public void update(Brand brand) throws BrandServiceException {
		try {
			brandRepository.save(brand);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BrandServiceException("更新品牌失败");
		}
	
	}

	@Override
	public void add(Brand brand) throws BrandServiceException {

		try {
			brandRepository.save(brand);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BrandServiceException("品牌保存失败");}
			
	}

	@Override
	public List<Brand> findAllBrandList() throws BrandServiceException {
		try {
			List<Brand> brandList = brandRepository.findAll();
			return brandList;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BrandServiceException("查询品牌失败");
		}
	}

}

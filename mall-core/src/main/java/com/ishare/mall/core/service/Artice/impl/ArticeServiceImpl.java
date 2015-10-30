package com.ishare.mall.core.service.Artice.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.exception.ArticeServiceException;
import com.ishare.mall.core.model.cms.Artice;
import com.ishare.mall.core.repository.information.ArticeRepository;
import com.ishare.mall.core.service.Artice.ArticeService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;
@Service
@Transactional
public class ArticeServiceImpl implements ArticeService {

	private static final Logger log = LoggerFactory.getLogger(ArticeServiceImpl.class);
	@Autowired
	private ArticeRepository articeRepository;

	
	public Page<Artice> search(Map<String, Object> searchParams,
			PageRequest pageRequest) throws ArticeServiceException {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Artice> spec = DynamicSpecifications.bySearchFilter(filters==null ? null:filters.values(), Artice.class);
		Page<Artice> page = articeRepository.findAll(spec,pageRequest);
		log.debug("filters:{}, total:{},content:{}",filters,page.getTotalElements(),page.getContent());
		return page;
	}
	
	@Override
	public Page<Artice> findByAllArtice(PageRequest pageRequest)
			throws ArticeServiceException {
		try {
			Page<Artice> page = articeRepository.findAll(null, pageRequest);
			return page;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ArticeServiceException("查询系统信息失败");
		}
	}
	
	

	@Override
	public void delArtice(Integer id) {
	}

}

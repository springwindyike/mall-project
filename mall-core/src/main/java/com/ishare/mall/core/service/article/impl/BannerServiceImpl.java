package com.ishare.mall.core.service.article.impl;

import com.ishare.mall.core.exception.BannerServiceException;
import com.ishare.mall.core.model.cms.Banner;
import com.ishare.mall.core.repository.information.BannerRepository;
import com.ishare.mall.core.service.article.BannerService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
/**
 * 系统栏目逻辑表
 * @author zhangzhaoxin
 *
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
	
	

	private static final Logger Log = LoggerFactory.getLogger(BannerServiceImpl.class);
	@Autowired
    private BannerRepository bannerRepository;

	
	
	@Override
	public Page<Banner> search(Map<String, Object> searchParams,
			PageRequest pageRequest) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Banner> spec = DynamicSpecifications.bySearchFilter(filters== null ?null:filters.values(), Banner.class);
		Page<Banner> page = bannerRepository.findAll(spec, pageRequest);
		Log.debug("filters:{}, total:{},content:{}",filters,page.getTotalElements(),page.getContent());
		return page;
	}
	
	public Logger getlog(){
		return Log;
		
	}

	@Override
	public Page<Banner> findAllBanner(PageRequest pageRequest)
			throws BannerServiceException {
			try {
				Page<Banner> page = bannerRepository.findAll(null, pageRequest);
				return page;
			} catch (Exception e) {
				Log.error(e.getMessage(),e);
				throw new BannerServiceException("查询系统栏目失败");
			}
	}

//	@Override
//	public List<Banner> findAllBannerList() throws BannerServiceException {
//		try {
//			List<Banner> bannerList = bannerRepository.findAll();
//			return bannerList;
//			
//		} catch (Exception e) {
//			Log.error(e.getMessage());
//			throw new BannerServiceException("查询系统栏目失败");
//		}
//	}

	@Override
	public void delBanner(Integer id) throws BannerServiceException {
		try {
			bannerRepository.delete(id);
		} catch (Exception e) {
			Log.error(e.getMessage());
			throw new BannerServiceException("删除系统栏目失败");
		}
	}

	@Override
	public void update(Banner banner) throws BannerServiceException {
		try {
		bannerRepository.save(banner);
		} catch (Exception e) {
			Log.error(e.getMessage());
			throw new BannerServiceException("更新系统栏目失败");
		}
	}


	@Override
	public void add(Banner banner) throws BannerServiceException {
		try {
			bannerRepository.save(banner);
		} catch (Exception e) {
			Log.error(e.getMessage());
			throw new BannerServiceException("添加系统栏目失败");
		}
	}

	@Override
	public Banner findOne(Integer id) {
		return bannerRepository.findOne(id);
	}

}

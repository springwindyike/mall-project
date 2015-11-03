package com.ishare.mall.core.service.artice;

import antlr.collections.List;

import com.ishare.mall.core.exception.BannerServiceException;
import com.ishare.mall.core.exception.BrandServiceException;
import com.ishare.mall.core.model.information.Brand;

/**
 * 系统栏目
 * @author zhangzhaoxin
 *
 */
public interface BannerService {
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 根据id删除系统栏目
	 * @param id
	 * @throws BannerServiceException
	 */
	public void delBanner(Integer id)throws BannerServiceException;
	/**
	 * 更新系统栏目
	 * @param brand
	 */
	void update(Brand brand)throws BannerServiceException;
	/**
	 * 添加系统栏目
	 * @param brand
	 */
	void add (Brand brand)throws BannerServiceException;
	
	
	
}

package com.ishare.mall.core.service.artice;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.exception.BannerServiceException;
import com.ishare.mall.core.exception.BrandServiceException;
import com.ishare.mall.core.model.cms.Banner;
import com.ishare.mall.core.model.information.Brand;

/**
 * 系统栏目
 * @author zhangzhaoxin
 *
 */
public interface BannerService {
	/**
	 * 通过条件查询系统栏目
	 * @param searchParams
	 * @param pageRequest
	 * @return
	 */
	public abstract Page<Banner> search(Map<String, Object>searchParams,PageRequest pageRequest); 
	/**
	 * 查看所有系统栏目
	 * @param pageRequest
	 * @return
	 * @throws BannerServiceException
	 */
	Page<Banner> findAllBanner(PageRequest pageRequest)throws BannerServiceException;
	/**
	 * 查看所有系统栏目
	 * @return
	 * @throws BannerServiceException
	 */
	
	List<Banner> findAllBannerList()throws BannerServiceException;
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
	void update(Banner banner)throws BannerServiceException;
	/**
	 * 添加系统栏目
	 * @param brand
	 */
	void add (Banner banner)throws BannerServiceException;
	
	
	
}

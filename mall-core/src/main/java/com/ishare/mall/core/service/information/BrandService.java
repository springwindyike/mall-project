package com.ishare.mall.core.service.information;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.exception.BrandServiceException;
import com.ishare.mall.core.model.information.Brand;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public abstract interface BrandService {
    /**
     * 根据ID查找品牌
     * @param id
     * @return
     */
    public abstract Brand findOne(Integer id);

    /**
     * 通过条件查询品牌
     * @param searchParams
     * @param pageRequest
     * @return
     */
    public abstract Page<Brand> search(Map<String, Object> searchParams, PageRequest pageRequest);
    
    /**
     * 查看所有品牌
     * @param 
     * @param pageRequest
     * @return
     */
    Page<Brand> findAllBrand(PageRequest pageRequest) throws BrandServiceException;
    
    /**
     * 查看所有品牌的list
     * @param 
     * @param 
     * @return
     */
    List<Brand> findAllBrandList() throws BrandServiceException;
    
    /**
     * 根据产品id删除品牌
     * @param id
     */
    public void delBrand(Integer id) throws BrandServiceException;
    
	/**
	 * 更新品牌信息
	 * @param brand
	 */
	void update(Brand brand)throws BrandServiceException;
	
    
	/**
	 * 添加品牌信息
	 * @param brand
	 */
	void add (Brand brand)throws BrandServiceException;
}

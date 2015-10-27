package com.ishare.mall.core.service.information;

import com.ishare.mall.core.exception.BrandServiceException;
import com.ishare.mall.core.model.information.Brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public abstract interface BrandService {
    /**
     * 根据ID查找商品类型
     * @param id
     * @return
     */
    public abstract Brand findOne(Integer id);

    /**
     * 通过条件查询商品
     * @param searchParams
     * @param pageRequest
     * @return
     */
    public abstract Page<Brand> search(Map<String, Object> searchParams, PageRequest pageRequest);
    
    /**
     * 查看所有品牌
     * @param channelId
     * @param pageRequest
     * @return
     */
    Page<Brand> findAllBrand(PageRequest pageRequest) throws BrandServiceException;;
}

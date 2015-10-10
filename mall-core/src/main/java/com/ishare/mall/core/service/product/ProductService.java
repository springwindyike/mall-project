package com.ishare.mall.core.service.product;

import com.ishare.mall.common.base.dto.product.FetchProductDTO;
import com.ishare.mall.core.exception.ProductServiceException;
import com.ishare.mall.core.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * Created by liaochenglei on 2015/9/22.
 * Description:
 * Version 1.0
 */
public abstract interface ProductService {

    public abstract Page<Product> search(Map<String, Object> searchParams, PageRequest pageRequest) throws ProductServiceException;

    /**
     * 根据id查看产品
     * @param id
     * @return
     */
    public abstract Product findOne(Integer id) throws ProductServiceException;
 
 /**
  * 根据code查找产品   
  * @param code
  * @return
  */
    Product findByCode(String code) throws ProductServiceException;

    /**
     * 查看渠道下所有产品
     * @param channelId
     * @param pageRequest
     * @return
     */
    Page<Product> findByChannelId(Integer channelId, PageRequest pageRequest) throws ProductServiceException;;

    /**
     * 根据产品id删除产品
     * @param id
     */
    public void delProduct(Integer id) throws ProductServiceException;
    
    
 /**
  * 保存产品
  * @param product
  */
    void saveProduct(Product product) throws ProductServiceException;
    
    /**
     * 保存产品
     * @param product
     */
    void updateProduct(Product product) throws ProductServiceException;

    /**
     * 处理爬虫商品
     * @param fetchProductDTO
     * @return
     * @throws ProductServiceException
     */
    Product process(FetchProductDTO fetchProductDTO) throws ProductServiceException;
}

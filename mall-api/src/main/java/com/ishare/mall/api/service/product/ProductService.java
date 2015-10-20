package com.ishare.mall.api.service.product;

import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductListDTO;

import java.util.Map;

/**
 * Created by wanghao on 2015/10/19.
 */
public interface ProductService {

    /**
     * 根据商品id查询商品详细信息
     * @param id
     * @return
     */
    ProductDetailDTO findOne(Integer id);
    /**
     * 根据商品code商品详细信息
     * @param code
     * @return
     */
    ProductDetailDTO findByCode(String code);

    /**
     * 根据当前页数 每页显示条数及查询条件查询商品
     * @param offset
     * @param limit
     * @return
     */
    PageDTO<ProductListDTO> findByPage(Integer offset,Integer limit,Map<String,Object> map);


}

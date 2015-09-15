package com.ishare.mall.biz.restful.product.brand;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.information.BrandDetailDTO;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.service.information.BrandService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/9/15.
 * Description : 品牌APP对外接口
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Brand.REQUEST_MAPPING)
public class BrandResource {

    private static final Logger log = LoggerFactory.getLogger(BrandResource.class);

    @Autowired
    private BrandService brandService;
    @RequestMapping(value = APPURIConstant.Brand.REQUEST_MAPPING_GET_BY_ID, method = RequestMethod.GET)
    public BrandDetailDTO get(@NotEmpty @PathVariable("id") Integer id) {
        //用findOne立即加载实体对象
        Brand brand = brandService.findOne(id);
        //转换为接口输出数据对象DTO 映射规则需要自己添加
        return (BrandDetailDTO) MapperUtils.map(brand, BrandDetailDTO.class);
    }
}

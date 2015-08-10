package com.ishare.mall.restful;

import com.ishare.mall.common.base.dto.information.BrandDetailDTO;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.service.information.BrandService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/8/10.
 * Description:品牌接口
 * Version 1.0
 */
@RestController
@RequestMapping("/brands")
public class BrandResource {
    @Autowired
    private BrandService brandService;
    /**
     * 品牌列表
     */
    public void list() {

    }

    /**
     * 获取品牌的详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BrandDetailDTO get(@NotEmpty @PathVariable("id") Integer id) {
        //用findOne立即加载实体对象
        Brand brand = brandService.findOne(id);
        if (brand != null) {
            //转换为接口输出数据对象DTO 映射规则需要自己添加
            return (BrandDetailDTO) MapperUtils.map(brand, BrandDetailDTO.class);
        }
        return null;
    }
}

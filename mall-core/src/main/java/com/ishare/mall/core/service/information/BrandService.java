package com.ishare.mall.core.service.information;

import com.ishare.mall.core.model.information.Brand;

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
}

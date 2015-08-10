package com.ishare.mall.core.service.information.impl;

import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.repository.information.BrandRepository;
import com.ishare.mall.core.service.information.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YinLin on 2015/8/10.
 * Description: 商品类别业务逻辑表
 * Version 1.0
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand findOne(Integer id) {
        return brandRepository.findOne(id);
    }
}

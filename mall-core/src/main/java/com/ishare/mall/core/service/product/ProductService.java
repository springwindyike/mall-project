package com.ishare.mall.core.service.product;

import com.ishare.mall.core.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by YinLin on 2015/7/30.
 * Description:
 * Version 1.0
 */
public abstract interface ProductService {

    public abstract Page<Product> search(Map<String, Object> searchParams, PageRequest pageRequest);

    public abstract Product getOne(Integer id);
}

package com.ishare.mall.core.service.product;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.product.Product;

/**
 * Created by YinLin on 2015/7/30.
 * Description:
 * Version 1.0
 */
public abstract interface ProductService {

    public abstract Page<Product> search(Map<String, Object> searchParams, PageRequest pageRequest);

    public abstract Product findOne(Integer id);

    Product findByCode(String code);
    public void delProduct(Integer id);
    /**
	 * 保存Product
	 *
	 * @return
	 */
    void saveProduct(Product product);
}

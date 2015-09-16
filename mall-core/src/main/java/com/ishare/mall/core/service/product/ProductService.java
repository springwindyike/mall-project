package com.ishare.mall.core.service.product;

import com.ishare.mall.core.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * Created by YinLin on 2015/7/30.
 * Description:
 * Version 1.0
 */
public abstract interface ProductService {

    public abstract Page<Product> search(Map<String, Object> searchParams, PageRequest pageRequest);

    public abstract Product findOne(Integer id);

    Product findByCode(String code);

    Page<Product> findByChannelId(Integer channelId, PageRequest pageRequest);

    public void delProduct(Integer id);
    /**
	 * 保存Product
	 *
	 * @return
	 */
    void saveProduct(Product product);
}

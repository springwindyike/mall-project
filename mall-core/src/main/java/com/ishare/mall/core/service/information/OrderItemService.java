package com.ishare.mall.core.service.information;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.model.order.OrderItem;
import com.ishare.mall.core.model.product.Product;

/**
 * Created by liaochenglei on 2015/8/18.
 * Description:
 * Version 1.0
 */
public abstract interface OrderItemService {

    public abstract Page<OrderItem> search(Map<String, Object> searchParams, PageRequest pageRequest);

}

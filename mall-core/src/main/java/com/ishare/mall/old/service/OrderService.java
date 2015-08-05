package com.ishare.mall.old.service;

import com.ishare.mall.old.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * Created by dongqi on 15/7/14.
 */
public interface OrderService {
    public Page<Order> search(Map<String, Object> searchParams, PageRequest pageRequest);
    public void deleteOne(Long id);
    public Order findOne(Long id);
}

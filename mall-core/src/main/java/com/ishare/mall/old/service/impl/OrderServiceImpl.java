package com.ishare.mall.old.service.impl;

import com.ishare.mall.core.utils.filter.SearchFilter;
import com.ishare.mall.old.model.Order;
import com.ishare.mall.old.repository.OrderRepository;
import com.ishare.mall.old.service.OrderService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * Created by dongqi on 15/7/14.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    public Page<Order> search(Map<String, Object> searchParams, PageRequest pageRequest){
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Order> spec = DynamicSpecifications.bySearchFilter(filters.values(), Order.class);
        Page<Order> page = orderRepository.findAll(spec, pageRequest);
        return page;
    }

    public void deleteOne(Long id){
         orderRepository.delete(id);

    }

    public Order findOne(Long id){
        return orderRepository.findOne(id);
    }
}

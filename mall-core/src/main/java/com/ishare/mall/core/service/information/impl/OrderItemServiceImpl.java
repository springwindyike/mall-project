package com.ishare.mall.core.service.information.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.order.OrderItem;
import com.ishare.mall.core.repository.information.OrderItemRepository;
import com.ishare.mall.core.service.information.OrderItemService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;

/**
 * Created by YinLin on 2015/8/10.
 * Description: 商品类别业务逻辑表
 * Version 1.0
 */
@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    private static final Logger log = LoggerFactory.getLogger(OrderItemServiceImpl.class);
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Page<OrderItem> search(Map<String, Object> searchParams, PageRequest pageRequest) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<OrderItem> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), OrderItem.class);
        Page<OrderItem> page = orderItemRepository.findAll(spec, pageRequest);
       	log.debug("filters: {}, total: {}, content: {}", filters, page.getTotalElements(), page.getContent());
        return page;
    }

    public Logger getLog() {
        return log;
    }

}

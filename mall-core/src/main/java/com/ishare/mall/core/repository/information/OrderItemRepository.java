package com.ishare.mall.core.repository.information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.order.OrderItem;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>, JpaSpecificationExecutor {
}

package com.ishare.mall.core.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ishare.mall.core.model.order.Order;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor {
    @Query("SELECT o FROM t_order o WHERE o.createBy=?1")
    List<Order> findByCreateBy(String createBy);
}

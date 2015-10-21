package com.ishare.mall.core.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.order.OrderActionLog;

/**
 * Created by Zhang Zhaoxin on 2015/9/25.
 * Description:
 * Version 1.0
 */
public interface OrderUpdateLogRepository extends JpaRepository<OrderActionLog, String>, JpaSpecificationExecutor {
    
}
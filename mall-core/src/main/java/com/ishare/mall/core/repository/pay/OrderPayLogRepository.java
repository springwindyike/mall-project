package com.ishare.mall.core.repository.pay;

import com.ishare.mall.core.model.pay.OrderPayLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by YinLin on 2015/8/27.
 * Description :
 * Version 1.0
 */
public interface OrderPayLogRepository extends JpaRepository<OrderPayLog, Integer>, JpaSpecificationExecutor {
    List<OrderPayLog> findByOrderId(String orderId);
}

package com.ishare.mall.core.repository.order;

import com.ishare.mall.core.model.order.OrderRefund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * Created by wanghao on 2015/11/10.
 */
public interface OrderRefundRepository extends JpaRepository<OrderRefund,String>,JpaSpecificationExecutor {}

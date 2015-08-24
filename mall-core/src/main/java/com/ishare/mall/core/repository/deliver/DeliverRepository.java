package com.ishare.mall.core.repository.deliver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.order.OrderDeliverInfo;

public interface DeliverRepository extends JpaRepository<OrderDeliverInfo, Integer>, JpaSpecificationExecutor {
}

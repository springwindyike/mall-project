package com.ishare.mall.old.repository;

import com.ishare.mall.old.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by dongqi on 15/7/13.
 */
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor {
}

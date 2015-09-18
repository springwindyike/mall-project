package com.ishare.mall.core.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ishare.mall.core.model.order.GeneratedOrderId;

/**
 * Created by Zhangzhaoxin on 2015/8/24.
 * Description:
 * Version 1.0
 */
public interface GeneratedOrderIdRepository extends JpaRepository<GeneratedOrderId, String>, JpaSpecificationExecutor {


	@Modifying
	@Query("update GeneratedOrderId oi set oi.id = ?1 where oi.orderId = ?2")
	int setFixedOrderIdFor(String id, String orderId);


}

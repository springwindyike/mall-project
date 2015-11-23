package com.ishare.mall.core.repository.order;

import com.ishare.mall.core.model.order.Order;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor {
    @Query("SELECT o FROM Order o WHERE o.createBy=?1")
    List<Order> findByCreateBy(String createBy);

	@Query("SELECT o FROM Order o WHERE o.channel.id = ?1")
	Page<Order> findByChannelId(Integer channelId, Pageable pageable);

	@Query("SELECT o FROM Order o WHERE o.sellerId = ?1")
	Page<Order> findBySellerId(Integer sellerId, Pageable pageable);

	@Query("SELECT o FROM Order o WHERE o.orderId like ?1 and o.channel.id = ?2")
	Page<Order> findBycondition(String orderId, Integer channelId, Pageable pageable);

	@Query("SELECT o FROM Order o WHERE o.orderId like ?1")
	Page<Order> findAllBycondition(String orderId, Pageable pageable);

	@Query("SELECT o FROM Order o WHERE o.createBy.account=?1 and o.channel.appId = ?2")
	Page<Order> findByCreateByAndChannelId(String account, String appId, Pageable pageable);

}
package com.ishare.mall.old.repository;

import com.ishare.mall.old.model.Cart;
import com.ishare.mall.old.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by dongqi on 15/7/13.
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query(value="select c from Cart c where c.customer = ?1")
	public Cart findCartByCustomer(Customer customer);
}

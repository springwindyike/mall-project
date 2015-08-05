package com.ishare.mall.old.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.old.model.Cart;
import com.ishare.mall.old.model.Customer;
import com.ishare.mall.old.repository.CartRepository;
import com.ishare.mall.old.service.CartService;

/**
 * Created by dongqi on 15/7/14.
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;

	/**
	 * 通过客户ID获取购物车
	 */
	public Cart findCartByCustomerId(Customer customer){
		Cart cart = cartRepository.findCartByCustomer(customer);
		//cartRepository.findOne(customer.getId());
		return cart;
	}
	public void save(Cart cart){
		cartRepository.save(cart);
	}
	
}

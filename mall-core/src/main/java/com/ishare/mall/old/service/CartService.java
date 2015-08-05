package com.ishare.mall.old.service;


import com.ishare.mall.old.model.Cart;
import com.ishare.mall.old.model.Customer;

/**
 * Created by dongqi on 15/7/14.
 */
public interface CartService {

	Cart findCartByCustomerId(Customer customer);

	void save(Cart cart);

}

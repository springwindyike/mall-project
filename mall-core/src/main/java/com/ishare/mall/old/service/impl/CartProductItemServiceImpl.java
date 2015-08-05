package com.ishare.mall.old.service.impl;

import com.ishare.mall.old.model.Cart;
import com.ishare.mall.old.model.CartProductItem;
import com.ishare.mall.old.model.Product;
import com.ishare.mall.old.repository.CartProductItemRepository;
import com.ishare.mall.old.service.CartProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dongqi on 15/7/14.
 */
@Service
@Transactional
public class CartProductItemServiceImpl implements CartProductItemService {
	
	@Autowired
	private CartProductItemRepository cartProductItemRepository;
	
	public void save(CartProductItem cartProduct){
		cartProductItemRepository.save(cartProduct);
	}
	public List<CartProductItem> findCartProductItemByCartId(Cart cart){
		return cartProductItemRepository.findCartProductItemByCart(cart);
	}
	
	public void updateCartProductItem(CartProductItem cartProductItem) {
		cartProductItemRepository.save(cartProductItem);
	}
	public CartProductItem findCartProductItemByProductId(Product product){
		List<CartProductItem> list= cartProductItemRepository.findCartProductItemByProductId(product);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	public void deleteCartProductItem(CartProductItem cartProductItem){
		cartProductItemRepository.delete(cartProductItem);
	}
	public CartProductItem findByCartAndProduct(Cart cart,Product product){
		return cartProductItemRepository.findByCartAndProduct(product, cart);
	}

	public CartProductItem findCartProductItemById(Long id) {
		return cartProductItemRepository.findOne(id);
	}
}

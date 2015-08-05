package com.ishare.mall.old.service;

import com.ishare.mall.old.model.Cart;
import com.ishare.mall.old.model.CartProductItem;
import com.ishare.mall.old.model.Product;

import java.util.List;

/**
 * Created by dongqi on 15/7/14.
 */
public interface CartProductItemService {

	void save(CartProductItem cartProduct);

	List<CartProductItem> findCartProductItemByCartId(Cart cart);

	void updateCartProductItem(CartProductItem cartProductItem);

	CartProductItem findCartProductItemByProductId(Product product);

	void deleteCartProductItem(CartProductItem cartProductItem);

	CartProductItem findByCartAndProduct(Cart cart, Product product);

	CartProductItem findCartProductItemById(Long id);
}

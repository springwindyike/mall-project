package com.ishare.mall.old.repository;

import com.ishare.mall.old.model.Cart;
import com.ishare.mall.old.model.CartProductItem;
import com.ishare.mall.old.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dongqi on 15/7/13.
 */
public interface CartProductItemRepository extends JpaRepository<CartProductItem, Long> {

	@Query("select c from CartProductItem c where c.cart = ?1")
	List<CartProductItem> findCartProductItemByCart(Cart cart);
	
	@Query("select c from CartProductItem c where c.product = ?1")
	List<CartProductItem> findCartProductItemByProductId(Product product);
	@Query("select c from CartProductItem c where c.product = ?1 and c.cart = ?2")
	CartProductItem findByCartAndProduct(Product product, Cart cart);
}

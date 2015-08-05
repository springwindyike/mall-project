package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "es_cart_products")
public class CartProductItem extends AbstractEntity {

	private int amount;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, updatable = true)
	private Product product;

	@ManyToOne
	@JoinColumn(name="cart_id", nullable = false, updatable = true)
	private Cart cart;
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}

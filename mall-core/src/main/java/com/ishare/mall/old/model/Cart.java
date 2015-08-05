package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dongqi on 15/7/12.
 *
 * 购物车
 */
@Entity
@Table(name = "es_cart")
public class Cart extends AbstractEntity {

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	//@JoinTable(name = "es_cart_products")
	@OneToMany
	@JoinColumn(name="product_id")
	private Set<CartProductItem> products;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<CartProductItem> getProducts() {
		return products;
	}

	public void setProducts(Set<CartProductItem> products) {
		this.products = products;
	}

}

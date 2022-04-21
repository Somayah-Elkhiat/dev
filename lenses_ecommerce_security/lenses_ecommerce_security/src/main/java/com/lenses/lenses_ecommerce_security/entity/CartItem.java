package com.lenses.lenses_ecommerce_security.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
@Entity
public class CartItem {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long itemId;
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Lenses lenses;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Cart cart;
	private CartItemStatus cartItemStatus;
	
	public CartItem() {
		super();
		
	}

	public CartItem(long itemId, int quantity, Lenses lenses) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.lenses = lenses;
	
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Lenses getLenses() {
		return lenses;
	}

	public void setLenses(Lenses lenses) {
		this.lenses = lenses;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	public CartItemStatus getCartItemStatus() {
		return cartItemStatus;
	}

	public void setCartItemStatus(CartItemStatus cartItemStatus) {
		this.cartItemStatus = cartItemStatus;
	}


	public enum CartItemStatus {
		AVAILABLE, OUTOFSTOCK, AVAILABLE_WITH_LESS_QUANTITY,
	}

	

}

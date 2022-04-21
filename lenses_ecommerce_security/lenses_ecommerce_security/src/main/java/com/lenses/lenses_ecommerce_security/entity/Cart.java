package com.lenses.lenses_ecommerce_security.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lenses.lenses_ecommerce_security.domain.User;

@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
@Entity
public class Cart {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long cartId;
	private Date createdDate;
	private Date modifiedDate;
	private int totalItems;
	private boolean enabled;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "cart")
	private List <CartItem> cartItem;
	

	
	private CartStatus cartStatus;
	
	
	public Cart() {
		super();
		
	}
	public Cart(long cartId, Date createdDate, Date modifiedDate, int totalItems, boolean enabled) {
		super();
		this.cartId = cartId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.totalItems = totalItems;
		this.enabled = enabled;
	
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getCartItem() {
		return this.cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
	
	
	
	public CartStatus getCartStatus() {
		return cartStatus;
	}
	public void setCartStatus(CartStatus cartStatus) {
		this.cartStatus = cartStatus;
	}


	public enum CartStatus {
		AVAILABLE, HAS_UNAVAILABLE_ITEM
	}
	

}

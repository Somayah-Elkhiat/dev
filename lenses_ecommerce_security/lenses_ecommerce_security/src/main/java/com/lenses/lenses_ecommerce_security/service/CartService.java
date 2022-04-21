package com.lenses.lenses_ecommerce_security.service;

import com.lenses.lenses_ecommerce_security.domain.User;
import com.lenses.lenses_ecommerce_security.entity.Cart;
import com.lenses.lenses_ecommerce_security.entity.CartItem;


public interface CartService {
	Cart createCart(User user);
	Cart addItemToCart(User user, String sku);
	Cart getActiveCart(User user);
//	public CartItem getCartItem(String sku);
	void changeQuantity(CartItem item, int quantity);
	void removeCartItem(long itemId);
	//void removeItemFromTheCart(Cart cart, CartItem item);
	void increaseItemQuantity(long quantity);
	Cart dicreaseItemQuantity(long itemId);
	double getTotalPrice(long cartId);
	double getItemPrice(long itemId);
	CartItem refreshCartItemStatus(long itemId);
	Cart refreshCart(long cartId);
	
}

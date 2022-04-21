package com.lenses.lenses_ecommerce_security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenses.lenses_ecommerce_security.domain.User;
import com.lenses.lenses_ecommerce_security.entity.Cart;
import com.lenses.lenses_ecommerce_security.entity.Cart.CartStatus;
import com.lenses.lenses_ecommerce_security.entity.CartItem;
import com.lenses.lenses_ecommerce_security.entity.CartItem.CartItemStatus;
import com.lenses.lenses_ecommerce_security.entity.Lenses;
import com.lenses.lenses_ecommerce_security.repository.CartItemRepository;
import com.lenses.lenses_ecommerce_security.repository.CartRepository;
import com.lenses.lenses_ecommerce_security.repository.LensesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private LensesRepository lensesRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private LensesService lensesService;

	@Override
	public Cart createCart(User user) {
		Cart cart = getActiveCart(user);
		if (cart == null) {
			cart = new Cart();
			cart.setCreatedDate(new Date());
			cart.setEnabled(true);
			cart.setUser(user);
			return cartRepository.save(cart);
		}
	

		return cart;
	}

	@Override
	public Cart addItemToCart(User user, String sku) {
		Cart cart = createCart(user);
		
		boolean inCart = false;
		
		// ;
		// check if the item is already in the cart before adding it again
		if (cart.getCartItem() != null) {
			for (CartItem item : cart.getCartItem()) {
				if (item.getLenses().getSku().equals(sku)) { // we already have this item in the cart
					increaseItemQuantity(item.getItemId());
					
					inCart = true;
					break;
				}
			}
		}
		if (!inCart || cart.getCartItem() == null) {
			CartItem cartItem = cartItemRepository.save(new CartItem());
			cartItem.setLenses(lensesRepository.getBySku(sku));
			increaseItemQuantity(cartItem.getItemId());
			cart.setTotalItems(cart.getTotalItems() + 1);
			cartItem.setCart(cart);
			if (cart.getCartItem() == null) {
				List<CartItem> items = new ArrayList<>();
				items.add(cartItem);
				cart.setCartItem(items);
			}
			else
				cart.getCartItem().add(cartItem);

		}
		cart.setModifiedDate(new Date());
		return cart;

	}

	@Override
	public Cart getActiveCart(User user) {
		if(user.getCarts() == null)
			return null;
		List<Cart> carts = user.getCarts();
		for (Cart cart : carts) {
			if (cart.isEnabled())
				return cart;
		}
		return null; // no active cart

	}

	@Override
	public void changeQuantity(CartItem item, int quantity) {

		item.setQuantity(item.getQuantity() + quantity);
		if (item.getQuantity() < 0) {
			removeCartItem(item.getItemId());
		}
	}

	@Override
	public void removeCartItem(long itemId) {
		CartItem item = cartItemRepository.getById(itemId);
		Cart cart = item.getCart();
		cart.setTotalItems(cart.getTotalItems() - 1);
		cartItemRepository.delete(item);
		cart.setModifiedDate(new Date());
		if (cart.getTotalItems() == 0) {
			cart.setEnabled(false);
		}

	}

	@Override
	public void increaseItemQuantity(long itemId) {
		CartItem item = cartItemRepository.getById(itemId);
		if (lensesService.getQuantity(item.getLenses().getSku()) > item.getQuantity()) {
			item.setQuantity(item.getQuantity() + 1);

		} else
			throw new RuntimeException("item is not available in requested quantity");

	}

	@Override
	public Cart dicreaseItemQuantity(long itemId) {
		CartItem item = cartItemRepository.getById(itemId);
		Cart cart = item.getCart();

		if (item.getQuantity() > 1) {
			item.setQuantity(item.getQuantity() - 1);
		} else
			removeCartItem(item.getItemId());
		return cart;

	}

	@Override
	public double getTotalPrice(long cartId) {
		Cart cart = cartRepository.getById(cartId);
		double totalPrice = 0;
		for (CartItem item : cart.getCartItem()) {
			totalPrice = totalPrice + getItemPrice(item.getItemId());

		}
		return totalPrice;

	}

	@Override
	public double getItemPrice(long itemId) {
		CartItem item = cartItemRepository.getById(itemId);
		Lenses lenses = item.getLenses();
		return (item.getQuantity() * lensesService.getPriceModel(lenses.getSku()).getSpecialPrice());
	}
	
	@Override
	public CartItem refreshCartItemStatus(long itemId) {
		CartItem item = cartItemRepository.getById(itemId);
		if(lensesService.getQuantity(item.getLenses().getSku()) <= 0) {
			item.setCartItemStatus(CartItemStatus.OUTOFSTOCK);
		}else if(lensesService.getQuantity(item.getLenses().getSku()) < item.getQuantity()) {
			item.setCartItemStatus(CartItemStatus.AVAILABLE_WITH_LESS_QUANTITY);
		}else {
			item.setCartItemStatus(CartItemStatus.AVAILABLE);
		}
		
		return cartItemRepository.save(item);
			
	}
	
	@Override
	public Cart refreshCart(long cartId) {
		Cart cart = cartRepository.getById(cartId);
		boolean flag = true;
		for(CartItem items: cart.getCartItem()) {
			refreshCartItemStatus(items.getItemId());
			if(!items.getCartItemStatus().equals(CartItemStatus.AVAILABLE)) {
				flag = false;
			}
		}
		if(flag)
			cart.setCartStatus(CartStatus.AVAILABLE);
		else
			cart.setCartStatus(CartStatus.HAS_UNAVAILABLE_ITEM);
		
		return cartRepository.save(cart);
		
	}
	
	

}

package com.lenses.lenses_ecommerce_security.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lenses.lenses_ecommerce_security.domain.User;
import com.lenses.lenses_ecommerce_security.entity.Cart;
import com.lenses.lenses_ecommerce_security.service.CartService;
import com.lenses.lenses_ecommerce_security.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CartController {
	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	
	
	@PostMapping("/add/item={sku}")
	public ResponseEntity<Cart> addItemToCart(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String sku) {
		User user = userService.getUser(request, response);
		Cart cart = cartService.addItemToCart(user, sku);
		return ResponseEntity.ok().body(cart);

	}
	
	@GetMapping("/Cart")
	public ResponseEntity<Cart> getCart(HttpServletRequest request, HttpServletResponse response){
		User user = userService.getUser(request, response);
		return ResponseEntity.ok().body(cartService.getActiveCart(user));
		
	}
	
	@PostMapping("/item/{itemId}/increase-quantity")
	public ResponseEntity<?> increaseItemQuantity(@PathVariable long itemId){
		cartService.increaseItemQuantity(itemId);
		return ResponseEntity.ok().build();	
	}
	
	@PostMapping("/item/{itemId}/dicrease-quantity")
	public ResponseEntity<Cart> dicreaseItemQuantity(@PathVariable long itemId){
		Cart cart = cartService.dicreaseItemQuantity(itemId);
		return ResponseEntity.ok().body(cart);	
	}
	
	@DeleteMapping("/item/{itemId}/delete")
	public ResponseEntity<?> DeleteItem(@PathVariable long itemId){
		cartService.removeCartItem(itemId);
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping("/total/cart/{cartId}/price")
	public ResponseEntity<Double> getTotalPrice(@PathVariable long cartId) {
		return ResponseEntity.ok().body(cartService.getTotalPrice(cartId));
		
	}
	
	@GetMapping("/total/item/{itemId}/price")
	public ResponseEntity<Double> getTotalItemPrice(@PathVariable long itemId) {
		return ResponseEntity.ok().body(cartService.getTotalPrice(itemId));
		
	}
	
	public ResponseEntity<Cart> refreshCart(long cartId){
		Cart cart = cartService.refreshCart(cartId);
		return ResponseEntity.ok().body(cart);
	}
	
	
	

}

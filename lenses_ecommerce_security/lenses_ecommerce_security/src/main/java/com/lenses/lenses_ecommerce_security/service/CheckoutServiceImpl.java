package com.lenses.lenses_ecommerce_security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.lenses.lenses_ecommerce_security.domain.User;
import com.lenses.lenses_ecommerce_security.entity.Cart;
import com.lenses.lenses_ecommerce_security.entity.Cart.CartStatus;

import com.lenses.lenses_ecommerce_security.entity.OrderItem;
import com.lenses.lenses_ecommerce_security.entity.CartItem;
import com.lenses.lenses_ecommerce_security.entity.Ordering;
import com.lenses.lenses_ecommerce_security.entity.Ordering.OrderStatus;
import com.lenses.lenses_ecommerce_security.proxy.MyFatoorahProxy;

import com.lenses.lenses_ecommerce_security.proxy.StripeProxy;
import com.lenses.lenses_ecommerce_security.entity.UserAddress;
import com.lenses.lenses_ecommerce_security.model.Amount;
import com.lenses.lenses_ecommerce_security.model.OrderModel;
import com.lenses.lenses_ecommerce_security.model.OrderRequest;
import com.lenses.lenses_ecommerce_security.model.OrderRequest.Intent;
import com.lenses.lenses_ecommerce_security.model.OrderResponse;
import com.lenses.lenses_ecommerce_security.model.PurchaseUnitRequest;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiatePaymentRequest;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiatePaymentResponse;
import com.lenses.lenses_ecommerce_security.repository.AreaRepository;
import com.lenses.lenses_ecommerce_security.repository.CartItemRepository;
import com.lenses.lenses_ecommerce_security.repository.CartRepository;
import com.lenses.lenses_ecommerce_security.repository.OrderItemRepository;
import com.lenses.lenses_ecommerce_security.repository.OrderRepository;
import com.lenses.lenses_ecommerce_security.repository.PaymentMethodRepository;
import com.lenses.lenses_ecommerce_security.repository.ShippingFeeRepository;
import com.lenses.lenses_ecommerce_security.repository.UserAddressRepository;
import com.lenses.lenses_ecommerce_security.repository.UserRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AreaRepository areaRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	UserAddressRepository userAddressRepository;
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	@Autowired
	CartService cartService;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ShippingFeeRepository shippingFeeRepository;
	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	LensesService lensesService;
	@Autowired
	StripeProxy stripeProxy;
	@Autowired
	MyFatoorahProxy myFatoorahProxy;
	@Value("${myfatoorah.access.token}")
	private String accessToken;

	@Override
	public List<UserAddress> getAddressListByUser(long userId) {
		User user = userRepository.getById(userId);
		return user.getAddress();
	}

	@Override
	public UserAddress addAddress(long userId, UserAddress address) {
		User user = userRepository.getById(userId);
		address.setUser(user);
		address.setArea(areaRepository.getById(address.getAreaPk()));
		address.setIsdefault(true);
		UserAddress saveAddress = userAddressRepository.save(address);
		setDefaultAddress(saveAddress.getAddressId());
		return saveAddress;

	}

	@Override
	public UserAddress setDefaultAddress(long addressId) {
		UserAddress address = userAddressRepository.getById(addressId);
		User user = address.getUser();
		address.setIsdefault(true);
		userAddressRepository.save(address);
		for (UserAddress addressInUser : user.getAddress()) {
			if (addressInUser.getAddressId() != address.getAddressId()) {
				addressInUser.setIsdefault(false);
				userAddressRepository.save(addressInUser);
			}
		}
		return address;
	}

	@Override
	public void removeAddress(long id) {
		UserAddress address = userAddressRepository.getById(id);
		User user = address.getUser();
		userAddressRepository.deleteById(id);
		if (address.isIsdefault()) {
			if (userAddressRepository.count() > 0) {
				for (UserAddress addressInUser : user.getAddress()) {
					addressInUser.setIsdefault(true);
					userAddressRepository.save(addressInUser);
				}
			}
		}

	}

	public UserAddress getDefaultAddress(long userId) {
		User user = userRepository.getById(userId);
		for (UserAddress address : user.getAddress()) {
			if (address.isIsdefault())
				return address;
		}
		return null;
	}

	public void updateStock(long cartId) {
		Cart cart = cartRepository.getById(cartId);
		for (CartItem item : cart.getCartItem()) {
			lensesService.removeQuantity(item.getLenses().getSku(), item.getQuantity());
		}

	}
	
	@Override
	public double getShippingFee(int areaId) {
		return shippingFeeRepository.getById(areaRepository.getById(areaId).getFee().getFeeId()).getFee();
		
	}
	
	

	@Override
	public InitiatePaymentResponse saveOrder(long userId, int paymentMethodId) {
		User user = userRepository.getById(userId);
		long cartId = cartService.getActiveCart(user).getCartId();
		Cart cart = cartService.refreshCart(cartId);

		if (cart.getCartStatus().equals(CartStatus.AVAILABLE)) {
			Ordering order = new Ordering();
			order.setUser(user);
			for (CartItem cartItem : cart.getCartItem()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setLenses(cartItem.getLenses());
				orderItem.setPrice(lensesService.getPriceModel(cartItem.getLenses().getSku()).getSpecialPrice());
				orderItem.setOrdering(order);
				orderItem.setQuantity(cartItem.getQuantity());
				if (order.getOrderItem() == null) {
					List<OrderItem> items = new ArrayList<>();
					items.add(orderItem);
					order.setOrderItem(items);
				} else
					order.getOrderItem().add(orderItem);

				orderItem = orderItemRepository.save(orderItem);

			}
			cart.setEnabled(false); // disable the cart
			updateStock(cart.getCartId());
			order.setOrderstatus(OrderStatus.CREATED);
			order.setAddress(getDefaultAddress(userId));
			order.setUuid(UUID.randomUUID());
			double shippingFee = order.getAddress().getArea().getFee().getFee();
			order.setTotalPrice(cartService.getTotalPrice(cart.getCartId()));
			order.setGrandTotalPrice(cartService.getTotalPrice(cart.getCartId()) + shippingFee);
			order.setPaymentMethod(paymentMethodRepository.getById(paymentMethodId));
			order.setCreatedDate(new Date());
			orderRepository.save(order);
			//OrderModel orderModel = new OrderModel(order.getGrandTotalPrice(), "usd", "paypal", "sale", "payment");
			//String paymentLink = paypalProxy.payment(orderModel);
			//Amount amount = new Amount("USD", ""+order.getGrandTotalPrice());
			//PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest(amount);
			//PurchaseUnitRequest[] purchaseUnitRequests = new PurchaseUnitRequest[] {purchaseUnitRequest}; 
			//OrderRequest orderRequest = new OrderRequest(Intent.CAPTURE, purchaseUnitRequests);
			InitiatePaymentRequest orderRequest = new InitiatePaymentRequest(order.getGrandTotalPrice(), "KWD");
			InitiatePaymentResponse initiatePaymentResponse = myFatoorahProxy.initiatePayment(orderRequest, "Bearer " + accessToken);
			if(initiatePaymentResponse.getIsSuccess())
				order.setOrderstatus(OrderStatus.CHECKOUT);
			return initiatePaymentResponse;
			//return paymentLink;

		} else{
			throw new RuntimeException("there are unavailable items in the cart");
		}

	}
	
	
	
	
	
	 

}

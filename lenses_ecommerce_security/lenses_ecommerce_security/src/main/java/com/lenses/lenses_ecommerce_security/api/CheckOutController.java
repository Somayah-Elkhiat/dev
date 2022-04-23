package com.lenses.lenses_ecommerce_security.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lenses.lenses_ecommerce_security.domain.User;
import com.lenses.lenses_ecommerce_security.entity.OrderItem;
import com.lenses.lenses_ecommerce_security.entity.OrderPayment;
import com.lenses.lenses_ecommerce_security.entity.Ordering;
import com.lenses.lenses_ecommerce_security.entity.Ordering.OrderStatus;
import com.lenses.lenses_ecommerce_security.entity.UserAddress;

import com.lenses.lenses_ecommerce_security.model.OrderResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.ExecutePaymentRequest;
import com.lenses.lenses_ecommerce_security.model.myfatoor.ExecutePaymentResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiatePaymentResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiateSessionRequest;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiateSessionResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InvoiceItem;
import com.lenses.lenses_ecommerce_security.model.myfatoor.PaymentStatusResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.PaymentStatustRequest;
import com.lenses.lenses_ecommerce_security.proxy.MyFatoorahProxy;
import com.lenses.lenses_ecommerce_security.repository.OrderPaymentRepository;
import com.lenses.lenses_ecommerce_security.repository.OrderRepository;
import com.lenses.lenses_ecommerce_security.service.CheckoutService;
import com.lenses.lenses_ecommerce_security.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CheckOutController {

	@Autowired
	UserService userService;
	@Autowired
	CheckoutService checkoutService;	
	
	InitiatePaymentResponse order;
	@Autowired
	MyFatoorahProxy myFatoorahProxy;

	@Value("${myfatoorah.access.token}")
	private String accessToken;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderPaymentRepository orderPaymentRepository;
	
	
	@GetMapping("address-list/by-user/{userId}")
	public ResponseEntity<List<UserAddress>> getAddressListBySpicificUser(@PathVariable long userId) {
		List<UserAddress> addresses = checkoutService.getAddressListByUser(userId);
		return ResponseEntity.ok().body(addresses);
			
	}

	@GetMapping("address-list/by-current-user")
	public ResponseEntity<List<UserAddress>> getAddressListByUser(HttpServletRequest request, HttpServletResponse response) {
		User user = userService.getUser(request, response);
		List<UserAddress> addresses = checkoutService.getAddressListByUser(user.getId());
		return ResponseEntity.ok().body(addresses);

	}
	
	@RequestMapping(value="add/address/to-current-user", method=RequestMethod.POST)
	public ResponseEntity<UserAddress> addAddressToUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserAddress address) {
		User user = userService.getUser(request, response);
		UserAddress addresses = checkoutService.addAddress(user.getId(), address);
		return ResponseEntity.ok().body(addresses);
	}
	
	@DeleteMapping("remove/address/{addressId}")
	public ResponseEntity<?> addAddressToUser(@PathVariable long addressId){
		checkoutService.removeAddress(addressId);
		return ResponseEntity.ok().build();
		
	}
	
	/*@PostMapping("save/order/payment={paymentMethodId}")
	public ResponseEntity<String> saveOrderToCheckOut(HttpServletRequest request, HttpServletResponse response, @PathVariable int paymentMethodId){
		User user = userService.getUser(request, response);
		String order = checkoutService.saveOrder(user.getId(), paymentMethodId);
		return ResponseEntity.ok().body(order);
	}
	*/
	@PostMapping("save/order/payment={paymentMethodId}")
	public ResponseEntity<InitiatePaymentResponse> saveOrderToCheckOut(HttpServletRequest request, HttpServletResponse response, @PathVariable int paymentMethodId){
		User user = userService.getUser(request, response);
		order = checkoutService.saveOrder(user.getId(), paymentMethodId);
		return ResponseEntity.ok().body(order);
		
	}
	
	@PostMapping("order/payment/order={orderId}")
	public ResponseEntity<ExecutePaymentResponse> executePayment( @PathVariable long orderId){
		//User user = userService.getUser(request, response);
		//InitiateSessionRequest initiateSessionRequest = new InitiateSessionRequest(user.getUsername());
		//InitiateSessionResponse initiateSessionResponse = myFatoorahProxy.initiateSession(initiateSessionRequest, "Bearer "+ accessToken);
		Ordering ordering = orderRepository.getById(orderId);
		List<InvoiceItem> invoiceItems = new ArrayList<>(); 
		for(OrderItem items : ordering.getOrderItem()) {
			InvoiceItem invoiceItem = new InvoiceItem();
			invoiceItem.setItemName(items.getLenses().getTitle());
			invoiceItem.setQuantity(items.getQuantity());
			invoiceItem.setUnitPrice(items.getPrice());
			invoiceItems.add(invoiceItem);
		}
		ExecutePaymentRequest executePaymentRequest = new ExecutePaymentRequest(ordering.getGrandTotalPrice(), 2);
		executePaymentRequest.setInvoiceItems(invoiceItems);
		ExecutePaymentResponse executePaymentResponse = myFatoorahProxy.executePayment(executePaymentRequest, "Bearer "+ accessToken);
		int invoiceId = executePaymentResponse.getData().getInvoiceId();
		
		//ordering.setInvoiceId(invoiceId);
		if(executePaymentResponse.getIsSuccess()) {
			orderRepository.getById(orderId).setOrderstatus(OrderStatus.PAYMENT);
			OrderPayment orderPayment = new OrderPayment();
			orderPaymentRepository.save(orderPayment);
			orderPayment.setInvoiceId(invoiceId);
			orderPayment.setOrder(ordering);
			ordering.setOrderPayment(orderPayment);
			orderPaymentRepository.save(orderPayment);
			orderRepository.save(ordering);
			
			
		}
		
		return ResponseEntity.ok().body(executePaymentResponse);
		
	}
	
	@PostMapping("/payment/status/order={orderId}")
	public ResponseEntity<OrderPayment> getPaymentStatus(@PathVariable long orderId){
		Ordering ordering = orderRepository.getById(orderId);
		OrderPayment orderPayment = orderPaymentRepository.getById(ordering.getOrderPayment().getId());
		String invoice = "" + orderPayment.getInvoiceId();
		PaymentStatustRequest paymentStatustRequest = new PaymentStatustRequest(invoice, "InvoiceId");
		PaymentStatusResponse paymentStatusResponse =  myFatoorahProxy.getPaymentStatust(paymentStatustRequest, "Bearer " + accessToken);
		orderPayment.setPaymentCreatedDate(paymentStatusResponse.getData().getCreatedDate());
		orderPayment.setPaymentExpiryDate(paymentStatusResponse.getData().getExpiryDate());
		orderPayment.setPaymentStatus(paymentStatusResponse.getData().getInvoiceStatus());
		
		return ResponseEntity.ok().body(orderPaymentRepository.save(orderPayment));
	}
	
	
	
	@GetMapping("shipping-fee/by-area/{areaId}")
	public ResponseEntity<Double> getShippingFee(int areaId){
		double shippingFee = checkoutService.getShippingFee(areaId);
		return ResponseEntity.ok().body(shippingFee);
		
	}
	
/*	@PostMapping("paypal/access/token")
	public String paypalAccessToken(){
		
		
		AccessTokenResponseModel model =  paypalProxy.generateAccessToken();
		return model.getAccessToken();
	}*/
	
	
	
	

}

package com.lenses.lenses_ecommerce_security.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lenses.lenses_ecommerce_security.model.PaymentIntentModel;



@FeignClient(name="stripe-payment", url="localhost:8003")
public interface StripeProxy {
	
	@PostMapping("/payment/information/{userName}/{userEmail}/total={amount}")
	public PaymentIntentModel createPayment(@PathVariable String userName, @PathVariable String userEmail,  @PathVariable long amount)  ;
	
}

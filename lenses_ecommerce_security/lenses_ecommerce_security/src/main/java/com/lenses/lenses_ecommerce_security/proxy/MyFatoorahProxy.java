package com.lenses.lenses_ecommerce_security.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lenses.lenses_ecommerce_security.model.myfatoor.ExecutePaymentRequest;
import com.lenses.lenses_ecommerce_security.model.myfatoor.ExecutePaymentResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiatePaymentRequest;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiatePaymentResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiateSessionRequest;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiateSessionResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.PaymentStatusResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.PaymentStatustRequest;

@FeignClient(name="My-Fatoorah", url="https://apitest.myfatoorah.com")
public interface MyFatoorahProxy {
	@PostMapping(value="/v2/InitiatePayment", consumes = MediaType.APPLICATION_JSON_VALUE)
	InitiatePaymentResponse initiatePayment(@RequestBody InitiatePaymentRequest request, @RequestHeader ("Authorization") String headerToken);
	
	@PostMapping(value="/v2/InitiateSession", consumes = MediaType.APPLICATION_JSON_VALUE)
	InitiateSessionResponse initiateSession(@RequestBody InitiateSessionRequest request, @RequestHeader ("Authorization") String headerToken);
	
	
	@PostMapping(value="/v2/ExecutePayment", consumes = MediaType.APPLICATION_JSON_VALUE)
	ExecutePaymentResponse executePayment(@RequestBody ExecutePaymentRequest executePaymentRequest, @RequestHeader ("Authorization") String headerToken);
	
	@PostMapping(value="/v2/GetPaymentStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
	PaymentStatusResponse getPaymentStatust(@RequestBody PaymentStatustRequest paymentStatusRequest, @RequestHeader ("Authorization") String headerToken);
	
	
	

}

package com.lenses.lenses_ecommerce_security.service;

import java.util.List;

import com.lenses.lenses_ecommerce_security.domain.User;
import com.lenses.lenses_ecommerce_security.entity.Area;
import com.lenses.lenses_ecommerce_security.entity.Ordering;
import com.lenses.lenses_ecommerce_security.entity.UserAddress;
import com.lenses.lenses_ecommerce_security.model.OrderResponse;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiatePaymentResponse;


public interface CheckoutService {
	List<UserAddress> getAddressListByUser(long userId);
	void removeAddress(long id);
	UserAddress addAddress(long userId, UserAddress Address);
	UserAddress setDefaultAddress(long addressId);
	InitiatePaymentResponse saveOrder(long userId, int paymentMethodId);
	double getShippingFee(int areaId);
	
}

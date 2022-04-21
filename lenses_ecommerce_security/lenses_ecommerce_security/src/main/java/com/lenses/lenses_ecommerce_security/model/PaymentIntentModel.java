package com.lenses.lenses_ecommerce_security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentIntentModel {
	private String paymentId;
	private long amount;
	private String currency;
	
	public PaymentIntentModel() {
		super();
		
	}
	public PaymentIntentModel(String customerId, long amount, String currency) {
		super();
		this.paymentId = customerId;
		this.amount = amount;
		this.currency = currency;
	}
	public String getCustomerId() {
		return paymentId;
	}
	public void setCustomerId(String customerId) {
		this.paymentId = customerId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	

}

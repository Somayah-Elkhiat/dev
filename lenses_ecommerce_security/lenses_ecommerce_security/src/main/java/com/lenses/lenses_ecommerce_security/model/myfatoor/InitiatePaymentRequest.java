package com.lenses.lenses_ecommerce_security.model.myfatoor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InitiatePaymentRequest {
	
	@JsonProperty("InvoiceAmount")
	private double invoiceAmount;
	@JsonProperty("CurrencyIso")
	private String currency;
	
	public InitiatePaymentRequest() {
		super();
		
	}
	public InitiatePaymentRequest(double invoiceAmount, String currency) {
		super();
		this.invoiceAmount = invoiceAmount;
		this.currency = currency;
	}
	public double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	

}

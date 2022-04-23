package com.lenses.lenses_ecommerce_security.model.myfatoor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecutePaymentRequest {
	@JsonProperty("InvoiceValue")
	private double invoiceValue;
	@JsonProperty("PaymentMethodId")
	private int paymentMethodId;
	@JsonProperty("InvoiceItem")
	private List<InvoiceItem> invoiceItems;
	
	
	public ExecutePaymentRequest() {
		super();
	}
	
	public ExecutePaymentRequest(double invoiceValue, int paymentMethodId) {
		super();
		this.invoiceValue = invoiceValue;
		this.paymentMethodId = paymentMethodId;
		
	}
	public double getInvoiceValue() {
		return invoiceValue;
	}
	public void setInvoiceValue(double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
	public int getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}
	
	

}

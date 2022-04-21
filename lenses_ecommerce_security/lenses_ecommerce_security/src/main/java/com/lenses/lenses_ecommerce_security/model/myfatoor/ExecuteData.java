package com.lenses.lenses_ecommerce_security.model.myfatoor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecuteData {
	@JsonProperty("InvoiceId")
	private int invoiceId;

	@JsonProperty("IsDirectPayment")
	private boolean isDirectPayment;
	
	@JsonProperty("PaymentURL")
	private String paymentUrl;
	
	@JsonProperty("CustomerRefrence")
	private String customerRefrence;
	
	@JsonProperty("UserDefinedField")
	private String userDefinedField;
	
	@JsonProperty("RecurringId")
	private String recurringId;

	public ExecuteData() {
		super();
		
	}

	public ExecuteData(int invoiceId, boolean isDirectPayment, String paymentUrl, String customerRefrence,
			String userDefinedField, String recurringId) {
		super();
		this.invoiceId = invoiceId;
		this.isDirectPayment = isDirectPayment;
		this.paymentUrl = paymentUrl;
		this.customerRefrence = customerRefrence;
		this.userDefinedField = userDefinedField;
		this.recurringId = recurringId;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public boolean isDirectPayment() {
		return isDirectPayment;
	}

	public void setDirectPayment(boolean isDirectPayment) {
		this.isDirectPayment = isDirectPayment;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	public String getCustomerRefrence() {
		return customerRefrence;
	}

	public void setCustomerRefrence(String customerRefrence) {
		this.customerRefrence = customerRefrence;
	}

	public String getUserDefinedField() {
		return userDefinedField;
	}

	public void setUserDefinedField(String userDefinedField) {
		this.userDefinedField = userDefinedField;
	}

	public String getRecurringId() {
		return recurringId;
	}

	public void setRecurringId(String recurringId) {
		this.recurringId = recurringId;
	}

}

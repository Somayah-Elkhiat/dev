package com.lenses.lenses_ecommerce_security.model;

public class PaymentResponse {
	private int invoiceId;
	private String url;
	private String customerReference;
	private String UserDefinedFeild;
	
	public PaymentResponse() {
		super();
		
	}
	
	public PaymentResponse(int invoiceId, String url, String customerReference, String userDefinedFeild) {
		super();
		this.invoiceId = invoiceId;
		this.url = url;
		this.customerReference = customerReference;
		UserDefinedFeild = userDefinedFeild;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCustomerReference() {
		return customerReference;
	}
	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}
	public String getUserDefinedFeild() {
		return UserDefinedFeild;
	}
	public void setUserDefinedFeild(String userDefinedFeild) {
		UserDefinedFeild = userDefinedFeild;
	}
	
	
}

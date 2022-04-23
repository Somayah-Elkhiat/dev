package com.lenses.lenses_ecommerce_security.model;


public class PaymentRequestModel {
	private double invoiceValue;
	private String customerName;
	private String notificationOption;
	

	public PaymentRequestModel() {
		super();
		
	}
	public PaymentRequestModel(double invoiceValue, String customerName, String notificationOption) {
		super();
		this.invoiceValue = invoiceValue;
		this.customerName = customerName;
		this.notificationOption = notificationOption;
	}
	public double getInvoiceValue() {
		return invoiceValue;
	}
	public void setInvoiceValue(double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getNotificationOption() {
		return notificationOption;
	}
	public void setNotificationOption(String notificationOption) {
		this.notificationOption = notificationOption;
	}
	
	

}

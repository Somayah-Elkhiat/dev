package com.lenses.lenses_ecommerce_security.model.myfatoor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMethods {
	@JsonProperty("PaymentMethodId")
	private int PaymentMethodId;
	
	@JsonProperty("PaymentMethodAr")
	private String PaymentMethodAr;
	
	@JsonProperty("PaymentMethodEn")
	private String PaymentMethodEn;
	
	@JsonProperty("PaymentMethodCode")
	private String PaymentMethodCode;
	
	@JsonProperty("IsDirectPayment")
	private boolean IsDirectPayment;
	
	@JsonProperty("ServiceCharge")
	private float ServiceCharge;
	
	@JsonProperty("TotalAmount")
	private float TotalAmount;
	
	@JsonProperty("CurrencyIso")
	private String CurrencyIso;
	
	@JsonProperty("ImageUrl")
	private String ImageUrl;
	
	@JsonProperty("IsEmbeddedSupported")
	private boolean IsEmbeddedSupported;
	
	@JsonProperty("PaymentCurrencyIso")
	private String PaymentCurrencyIso;

	// Getter Methods
	public PaymentMethods() {
		super();
		
	}
	public int getPaymentMethodId() {
		return PaymentMethodId;
	}

	public PaymentMethods(int paymentMethodId, String paymentMethodAr, String paymentMethodEn,
			String paymentMethodCode, boolean isDirectPayment, float serviceCharge, float totalAmount,
			String currencyIso, String imageUrl, boolean isEmbeddedSupported, String paymentCurrencyIso) {
		super();
		PaymentMethodId = paymentMethodId;
		PaymentMethodAr = paymentMethodAr;
		PaymentMethodEn = paymentMethodEn;
		PaymentMethodCode = paymentMethodCode;
		IsDirectPayment = isDirectPayment;
		ServiceCharge = serviceCharge;
		TotalAmount = totalAmount;
		CurrencyIso = currencyIso;
		ImageUrl = imageUrl;
		IsEmbeddedSupported = isEmbeddedSupported;
		PaymentCurrencyIso = paymentCurrencyIso;
	}

	public String getPaymentMethodAr() {
		return PaymentMethodAr;
	}

	public String getPaymentMethodEn() {
		return PaymentMethodEn;
	}

	public String getPaymentMethodCode() {
		return PaymentMethodCode;
	}

	public boolean getIsDirectPayment() {
		return IsDirectPayment;
	}

	public float getServiceCharge() {
		return ServiceCharge;
	}

	public float getTotalAmount() {
		return TotalAmount;
	}

	public String getCurrencyIso() {
		return CurrencyIso;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public boolean getIsEmbeddedSupported() {
		return IsEmbeddedSupported;
	}

	public String getPaymentCurrencyIso() {
		return PaymentCurrencyIso;
	}

	// Setter Methods

	public void setPaymentMethodId(int PaymentMethodId) {
		this.PaymentMethodId = PaymentMethodId;
	}

	public void setPaymentMethodAr(String PaymentMethodAr) {
		this.PaymentMethodAr = PaymentMethodAr;
	}

	public void setPaymentMethodEn(String PaymentMethodEn) {
		this.PaymentMethodEn = PaymentMethodEn;
	}

	public void setPaymentMethodCode(String PaymentMethodCode) {
		this.PaymentMethodCode = PaymentMethodCode;
	}

	public void setIsDirectPayment(boolean IsDirectPayment) {
		this.IsDirectPayment = IsDirectPayment;
	}

	public void setServiceCharge(float ServiceCharge) {
		this.ServiceCharge = ServiceCharge;
	}

	public void setTotalAmount(float TotalAmount) {
		this.TotalAmount = TotalAmount;
	}

	public void setCurrencyIso(String CurrencyIso) {
		this.CurrencyIso = CurrencyIso;
	}

	public void setImageUrl(String ImageUrl) {
		this.ImageUrl = ImageUrl;
	}

	public void setIsEmbeddedSupported(boolean IsEmbeddedSupported) {
		this.IsEmbeddedSupported = IsEmbeddedSupported;
	}

	public void setPaymentCurrencyIso(String PaymentCurrencyIso) {
		this.PaymentCurrencyIso = PaymentCurrencyIso;
	}
}

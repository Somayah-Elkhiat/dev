package com.lenses.lenses_ecommerce_security.model.myfatoor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceTransaction {
	@JsonProperty("TransactionDate")
	private String TransactionDate;
	@JsonProperty("PaymentGateway")
	private String PaymentGateway;
	@JsonProperty("ReferenceId")
	private String ReferenceId;
	@JsonProperty("TrackId")
	private String TrackId;
	@JsonProperty("TransactionId")
	private String TransactionId;
	@JsonProperty("PaymentId")
	private String PaymentId;
	@JsonProperty("AuthorizationId")
	private String AuthorizationId;
	@JsonProperty("TransactionStatus")
	private String TransactionStatus;
	@JsonProperty("TransationValue")
	private String TransationValue;
	@JsonProperty("CustomerServiceCharge")
	private String CustomerServiceCharge;
	@JsonProperty("DueValue")
	private String DueValue;
	@JsonProperty("PaidCurrency")
	private String PaidCurrency;
	@JsonProperty("PaidCurrencyValue")
	private String PaidCurrencyValue;
	@JsonProperty("IpAddress")
	private String IpAddress;
	@JsonProperty("Country")
	private String Country;
	@JsonProperty("Currency")
	private String Currency;
	@JsonProperty("Error")
	private String Error;
	@JsonProperty("CardNumber")
	private String CardNumber;
	@JsonProperty("ErrorCode")
	private String ErrorCode;

	// Getter Methods

	public String getTransactionDate() {
		return TransactionDate;
	}

	public String getPaymentGateway() {
		return PaymentGateway;
	}

	public String getReferenceId() {
		return ReferenceId;
	}

	public String getTrackId() {
		return TrackId;
	}

	public String getTransactionId() {
		return TransactionId;
	}

	public String getPaymentId() {
		return PaymentId;
	}

	public String getAuthorizationId() {
		return AuthorizationId;
	}

	public String getTransactionStatus() {
		return TransactionStatus;
	}

	public String getTransationValue() {
		return TransationValue;
	}

	public String getCustomerServiceCharge() {
		return CustomerServiceCharge;
	}

	public String getDueValue() {
		return DueValue;
	}

	public String getPaidCurrency() {
		return PaidCurrency;
	}

	public String getPaidCurrencyValue() {
		return PaidCurrencyValue;
	}

	public String getIpAddress() {
		return IpAddress;
	}

	public String getCountry() {
		return Country;
	}

	public String getCurrency() {
		return Currency;
	}

	public String getError() {
		return Error;
	}

	public String getCardNumber() {
		return CardNumber;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	// Setter Methods

	public void setTransactionDate(String TransactionDate) {
		this.TransactionDate = TransactionDate;
	}

	public void setPaymentGateway(String PaymentGateway) {
		this.PaymentGateway = PaymentGateway;
	}

	public void setReferenceId(String ReferenceId) {
		this.ReferenceId = ReferenceId;
	}

	public void setTrackId(String TrackId) {
		this.TrackId = TrackId;
	}

	public void setTransactionId(String TransactionId) {
		this.TransactionId = TransactionId;
	}

	public void setPaymentId(String PaymentId) {
		this.PaymentId = PaymentId;
	}

	public void setAuthorizationId(String AuthorizationId) {
		this.AuthorizationId = AuthorizationId;
	}

	public void setTransactionStatus(String TransactionStatus) {
		this.TransactionStatus = TransactionStatus;
	}

	public void setTransationValue(String TransationValue) {
		this.TransationValue = TransationValue;
	}

	public void setCustomerServiceCharge(String CustomerServiceCharge) {
		this.CustomerServiceCharge = CustomerServiceCharge;
	}

	public void setDueValue(String DueValue) {
		this.DueValue = DueValue;
	}

	public void setPaidCurrency(String PaidCurrency) {
		this.PaidCurrency = PaidCurrency;
	}

	public void setPaidCurrencyValue(String PaidCurrencyValue) {
		this.PaidCurrencyValue = PaidCurrencyValue;
	}

	public void setIpAddress(String IpAddress) {
		this.IpAddress = IpAddress;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}

	public void setCurrency(String Currency) {
		this.Currency = Currency;
	}

	public void setError(String Error) {
		this.Error = Error;
	}

	public void setCardNumber(String CardNumber) {
		this.CardNumber = CardNumber;
	}

	public void setErrorCode(String ErrorCode) {
		this.ErrorCode = ErrorCode;
	}
}

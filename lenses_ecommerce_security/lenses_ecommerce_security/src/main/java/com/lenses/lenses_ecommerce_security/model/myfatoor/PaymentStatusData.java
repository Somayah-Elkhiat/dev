package com.lenses.lenses_ecommerce_security.model.myfatoor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentStatusData {
	@JsonProperty("InvoiceId")
	private int invoiceId;
	@JsonProperty("InvoiceStatus")
	private String invoiceStatus;
	@JsonProperty("InvoiceReference")
	private String invoiceReference;
	@JsonProperty("CreatedDate")
	private String createdDate;
	@JsonProperty("ExpiryDate")
	private String expiryDate;
	
	@JsonProperty("CustomerReference")
	private String CustomerReference;
	@JsonProperty("ExpiryTime")
	private String ExpiryTime;
	@JsonProperty("InvoiceValue")
	private float InvoiceValue;
	@JsonProperty("Comments")
	private String Comments;
	@JsonProperty("CustomerName")
	private String CustomerName;
	@JsonProperty("CustomerMobile")
	private String CustomerMobile;
	@JsonProperty("CustomerEmail")
	private String CustomerEmail;
	@JsonProperty("UserDefinedField")
	private String UserDefinedField;
	@JsonProperty("InvoiceDisplayValue")
	private String InvoiceDisplayValue;
	@JsonProperty("DepositStatus")
	private String DepositStatus;
	
	List<InvoiceItem> InvoiceItems = new ArrayList<>();
	List<InvoiceTransaction> InvoiceTransactions = new ArrayList<>();
	List<Supplier> Suppliers = new ArrayList<>();

	// Getter Methods


	public String getCustomerReference() {
		return CustomerReference;
	}

	

	public String getExpiryTime() {
		return ExpiryTime;
	}

	public float getInvoiceValue() {
		return InvoiceValue;
	}

	public String getComments() {
		return Comments;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public String getCustomerMobile() {
		return CustomerMobile;
	}

	public String getCustomerEmail() {
		return CustomerEmail;
	}

	public String getUserDefinedField() {
		return UserDefinedField;
	}

	public String getInvoiceDisplayValue() {
		return InvoiceDisplayValue;
	}

	public String getDepositStatus() {
		return DepositStatus;
	}

	// Setter Methods

	

	public void setCustomerReference(String CustomerReference) {
		this.CustomerReference = CustomerReference;
	}

	

	public void setExpiryTime(String ExpiryTime) {
		this.ExpiryTime = ExpiryTime;
	}

	public void setInvoiceValue(float InvoiceValue) {
		this.InvoiceValue = InvoiceValue;
	}

	public void setComments(String Comments) {
		this.Comments = Comments;
	}

	public void setCustomerName(String CustomerName) {
		this.CustomerName = CustomerName;
	}

	public void setCustomerMobile(String CustomerMobile) {
		this.CustomerMobile = CustomerMobile;
	}

	public void setCustomerEmail(String CustomerEmail) {
		this.CustomerEmail = CustomerEmail;
	}

	public void setUserDefinedField(String UserDefinedField) {
		this.UserDefinedField = UserDefinedField;
	}

	public void setInvoiceDisplayValue(String InvoiceDisplayValue) {
		this.InvoiceDisplayValue = InvoiceDisplayValue;
	}

	public void setDepositStatus(String DepositStatus) {
		this.DepositStatus = DepositStatus;
	}

	

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getInvoiceReference() {
		return invoiceReference;
	}

	public void setInvoiceReference(String invoiceReference) {
		this.invoiceReference = invoiceReference;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

}

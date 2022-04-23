package com.lenses.lenses_ecommerce_security.model.myfatoor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Supplier {
	@JsonProperty("SupplierCode")
	private float SupplierCode;
	@JsonProperty("SupplierName")
	private String SupplierName;
	@JsonProperty("InvoiceShare")
	private float InvoiceShare;
	@JsonProperty("ProposedShare")
	private float ProposedShare;
	@JsonProperty("DepositShare")
	private float DepositShare;

	// Getter Methods

	public float getSupplierCode() {
		return SupplierCode;
	}

	public String getSupplierName() {
		return SupplierName;
	}

	public float getInvoiceShare() {
		return InvoiceShare;
	}

	public float getProposedShare() {
		return ProposedShare;
	}

	public float getDepositShare() {
		return DepositShare;
	}

	// Setter Methods

	public void setSupplierCode(float SupplierCode) {
		this.SupplierCode = SupplierCode;
	}

	public void setSupplierName(String SupplierName) {
		this.SupplierName = SupplierName;
	}

	public void setInvoiceShare(float InvoiceShare) {
		this.InvoiceShare = InvoiceShare;
	}

	public void setProposedShare(float ProposedShare) {
		this.ProposedShare = ProposedShare;
	}

	public void setDepositShare(float DepositShare) {
		this.DepositShare = DepositShare;
	}

}

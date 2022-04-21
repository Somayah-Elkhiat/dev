package com.lenses.lenses_ecommerce_security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseUnitRequest {
	private Amount amount;

	public PurchaseUnitRequest() {
		super();
		
	}
	
	public PurchaseUnitRequest(Amount amount) {
		super();
		this.amount = amount;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	

}

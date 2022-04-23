package com.lenses.lenses_ecommerce_security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {
	
	private Intent intent;
	private PurchaseUnitRequest[] purchaseUnit;
	
	
	public OrderRequest() {
		super();
		
	}

	
	public OrderRequest(Intent intent, PurchaseUnitRequest[] purchaseUnit) {
		super();
		this.intent = intent;
		this.purchaseUnit = purchaseUnit;
	}




	public Intent getIntent() {
		return intent;
	}




	public void setIntent(Intent intent) {
		this.intent = intent;
	}




	public PurchaseUnitRequest[] getPurchaseUnit() {
		return purchaseUnit;
	}




	public void setPurchaseUnit(PurchaseUnitRequest[] purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}




	public enum Intent{
		CAPTURE, AUTHORIZE,
	}

}

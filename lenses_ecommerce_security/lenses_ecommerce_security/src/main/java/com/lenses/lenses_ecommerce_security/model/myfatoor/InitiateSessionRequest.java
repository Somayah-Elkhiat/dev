package com.lenses.lenses_ecommerce_security.model.myfatoor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InitiateSessionRequest {
	@JsonProperty("CustomerIdentifier")
	private String customerIdentifier;

	public InitiateSessionRequest() {
		super();
	}
	public InitiateSessionRequest(String customerIdentefier) {
		super();
		this.customerIdentifier = customerIdentefier;
	}

	public String getCustomerIdentefier() {
		return customerIdentifier;
	}

	public void setCustomerIdentefier(String customerIdentefier) {
		this.customerIdentifier = customerIdentefier;
	}

}

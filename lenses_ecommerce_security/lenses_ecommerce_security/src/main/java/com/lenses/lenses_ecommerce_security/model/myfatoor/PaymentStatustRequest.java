package com.lenses.lenses_ecommerce_security.model.myfatoor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentStatustRequest {
	@JsonProperty("Key")
	private String key;
	
	@JsonProperty("KeyType")
	private String keyType;
	
	public PaymentStatustRequest() {
		super();
		
	}
	public PaymentStatustRequest(String key, String keyType) {
		super();
		this.key = key;
		this.keyType = keyType;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	
	
	

}

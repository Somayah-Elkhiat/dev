package com.lenses.lenses_ecommerce_security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Amount {
	private String currencyCode;
	private String value;
	
	public Amount() {
		super();
		
	}
	
	public Amount(String currencyCode, String value) {
		super();
		this.currencyCode = currencyCode;
		this.value = value;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}

package com.lenses.lenses_ecommerce_security.model.myfatoor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationErrors {
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Error")
	private String error;
	
	public ValidationErrors() {
		super();
		
	}
	public ValidationErrors(String name, String erroe) {
		super();
		this.name = name;
		this.error = erroe;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getErroe() {
		return error;
	}
	public void setErroe(String erroe) {
		this.error = erroe;
	}
	
	

}

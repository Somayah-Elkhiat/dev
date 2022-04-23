package com.lenses.lenses_ecommerce_security.model.myfatoor;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ExecutePaymentResponse {
	@JsonProperty("IsSuccess")
	private boolean isSuccess;
	@JsonProperty("Message")
	private String message;
	@JsonProperty("ValidationErrors")
	private ArrayList<ValidationErrors> ValidationErrors = new ArrayList<>();
	@JsonProperty("Data")
	private ExecuteData data;

	// Getter Methods

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public ExecuteData getData() {
		return data;
	}

	// Setter Methods

	public void setIsSuccess(boolean IsSuccess) {
		this.isSuccess = IsSuccess;
	}

	public void setMessage(String Message) {
		this.message = Message;
	}

	public void setData(ExecuteData DataObject) {
		this.data = DataObject;
	}
	
	
}

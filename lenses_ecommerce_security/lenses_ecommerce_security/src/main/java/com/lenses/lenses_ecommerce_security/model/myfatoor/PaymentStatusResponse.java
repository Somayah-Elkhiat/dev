package com.lenses.lenses_ecommerce_security.model.myfatoor;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentStatusResponse {
	@JsonProperty("IsSuccess")
	private boolean isSuccess;
	@JsonProperty("Message")
	private String message;
	@JsonProperty("ValidationErrors")
	private ArrayList<ValidationErrors> ValidationErrors = new ArrayList<>();
	@JsonProperty("Data")
	private PaymentStatusData data;

	// Getter Methods

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public PaymentStatusData getData() {
		return data;
	}

	// Setter Methods

	public void setIsSuccess(boolean IsSuccess) {
		this.isSuccess = IsSuccess;
	}

	public void setMessage(String Message) {
		this.message = Message;
	}

	public void setData(PaymentStatusData DataObject) {
		this.data = DataObject;
	}


}

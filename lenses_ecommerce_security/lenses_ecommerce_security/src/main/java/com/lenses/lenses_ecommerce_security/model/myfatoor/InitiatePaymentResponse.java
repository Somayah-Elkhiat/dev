package com.lenses.lenses_ecommerce_security.model.myfatoor;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InitiatePaymentResponse {
	@JsonProperty("IsSuccess")
	private boolean isSuccess;
	@JsonProperty("Message")
	private String message;
	@JsonProperty("ValidationErrors")
	ArrayList<ValidationErrors> ValidationErrors = new ArrayList<>();
	@JsonProperty("Data")
	Data data;

	// Getter Methods

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public Data getData() {
		return data;
	}

	// Setter Methods

	public void setIsSuccess(boolean IsSuccess) {
		this.isSuccess = IsSuccess;
	}

	public void setMessage(String Message) {
		this.message = Message;
	}

	public void setData(Data DataObject) {
		this.data = DataObject;
	}
	
	public class Data {
		@JsonProperty("PaymentMethods")
		ArrayList<PaymentMethods> payments = new ArrayList<>();

		public ArrayList<PaymentMethods> getPayments() {
			return payments;
		}

		public void setPayments(ArrayList<PaymentMethods> payments) {
			this.payments = payments;
		}
		
		

	}
}
// public class Data {
// ArrayList <PaymentMethods> PaymentMethods = new ArrayList < > ();

// Getter Methods

// Setter Methods

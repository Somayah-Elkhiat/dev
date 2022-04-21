package com.lenses.lenses_ecommerce_security.model.myfatoor;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lenses.lenses_ecommerce_security.model.myfatoor.InitiatePaymentResponse.Data;

public class InitiateSessionResponse {
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
	
	public class Data{
		@JsonProperty("SessionId")
		private String sessionId;
		@JsonProperty("CountryCode")
		private String countryCode;
		
		public Data() {
			super();
		}
		public Data(String sessionId, String countryCode) {
			super();
			this.sessionId = sessionId;
			this.countryCode = countryCode;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		
		
	}

}

package com.lenses.lenses_ecommerce_security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {
	private String createTime;
	private String id;
	private Intent intent;
	private LinkDescription[] links;
	
	public OrderResponse() {
		super();
	
	}
	
	public OrderResponse(String createTime, String id, Intent intent, LinkDescription[] links) {
		super();
		this.createTime = createTime;
		this.id = id;
		this.intent = intent;
		this.links = links;
	}



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Intent getIntent() {
		return intent;
	}



	public void setIntent(Intent intent) {
		this.intent = intent;
	}



	public LinkDescription[] getLinks() {
		return links;
	}



	public void setLinks(LinkDescription[] links) {
		this.links = links;
	}



	public enum Intent{
		CAPTURE, AUTHORIZE,
	}

}

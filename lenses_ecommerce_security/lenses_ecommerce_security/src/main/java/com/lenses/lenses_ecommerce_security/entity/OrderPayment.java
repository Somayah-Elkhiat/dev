package com.lenses.lenses_ecommerce_security.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
@Entity
public class OrderPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Ordering order;
	private int invoiceId;
	private String paymentCreatedDate;
	private String paymentExpiryDate;
	private String paymentStatus;
	public OrderPayment() {
		super();
		
	}
	
	public OrderPayment(long id, int invoiceId, String paymentCreatedDate, String paymentExpiryDate,
			String paymentStatus) {
		super();
		this.id = id;
		this.invoiceId = invoiceId;
		this.paymentCreatedDate = paymentCreatedDate;
		this.paymentExpiryDate = paymentExpiryDate;
		this.paymentStatus = paymentStatus;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getPaymentCreatedDate() {
		return paymentCreatedDate;
	}
	public void setPaymentCreatedDate(String paymentCreatedDate) {
		this.paymentCreatedDate = paymentCreatedDate;
	}
	public String getPaymentExpiryDate() {
		return paymentExpiryDate;
	}
	public void setPaymentExpiryDate(String paymentExpiryDate) {
		this.paymentExpiryDate = paymentExpiryDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public Ordering getOrder() {
		return order;
	}

	public void setOrder(Ordering order) {
		this.order = order;
	}
	
	

}

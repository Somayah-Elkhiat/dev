package com.lenses.lenses_ecommerce_security.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String paymentMethod;
	
	@OneToMany(mappedBy = "payment")
	@JsonIgnore
	private List<Ordering> orders ;
	
	
	public Payment() {
		super();
		
	}
	
	public Payment(int paymentMethodId, String paymentMethod) {
		super();
		this.id = paymentMethodId;
		this.paymentMethod = paymentMethod;
	}
	public int getPaymentMethodId() {
		return id;
	}
	public void setPaymentMethodId(int paymentMethodId) {
		this.id = paymentMethodId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<Ordering> getOrders() {
		return orders;
	}

	public void setOrders(List<Ordering> orders) {
		this.orders = orders;
	}
	
	
	
	
}

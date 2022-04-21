package com.lenses.lenses_ecommerce_security.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lenses.lenses_ecommerce_security.domain.User;

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Ordering {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@GeneratedValue
	@Column(unique = true)
	private UUID uuid;
	
	private double totalPrice;
	private double grandTotalPrice;
	
	private OrderStatus orderstatus;
	private Date createdDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserAddress address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Payment payment;
	
	@OneToMany(mappedBy = "ordering", cascade=CascadeType.ALL)
	private List <OrderItem> orderItem;
	
	@OneToOne
	@JsonIgnore
	private OrderPayment orderPayment;

	public Ordering() {
		super();
		
	}

	public Ordering(long orderId, UUID uuid, double totalPrice) {
		super();
		this.id = orderId;
		this.uuid = uuid;
		this.totalPrice = totalPrice;
		
	}

	public long getOrderId() {
		return id;
	}

	public void setOrderId(long orderId) {
		this.id = orderId;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserAddress getAddress() {
		return address;
	}

	public void setAddress(UserAddress address) {
		this.address = address;
	}

	public Payment getPaymentMethod() {
		return payment;
	}

	public void setPaymentMethod(Payment paymentMethod) {
		this.payment= paymentMethod;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}


	

	public List <OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List <OrderItem> orderItem) {
		this.orderItem = orderItem;
	}




	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}




	public double getGrandTotalPrice() {
		return grandTotalPrice;
	}

	public void setGrandTotalPrice(double grandTotalPrice) {
		this.grandTotalPrice = grandTotalPrice;
	}






	public OrderPayment getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(OrderPayment orderPayment) {
		this.orderPayment = orderPayment;
	}






	public enum OrderStatus{
		CREATED,
		CHECKOUT,
		CANCELED,
		PAYMENT
	}
	
	
	
	
	

}

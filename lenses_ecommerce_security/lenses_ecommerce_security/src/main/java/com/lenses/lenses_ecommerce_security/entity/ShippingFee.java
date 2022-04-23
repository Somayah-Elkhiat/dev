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
public class ShippingFee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double fee;
	
	@OneToMany(mappedBy = "fee")
	@JsonIgnore
	private List<Area> areas ;
	
	public ShippingFee() {
		super();
		
	}

	public ShippingFee(int feeId, double fee) {
		super();
		this.id = feeId;
		this.fee = fee;
	}

	public int getFeeId() {
		return id;
	}

	public void setFeeId(int feeId) {
		this.id = feeId;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
	
	

}

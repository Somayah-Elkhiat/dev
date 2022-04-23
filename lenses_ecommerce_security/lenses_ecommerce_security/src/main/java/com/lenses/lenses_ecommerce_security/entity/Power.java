package com.lenses.lenses_ecommerce_security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class Power {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="power_id")
	private int powerId;
	private double power;
	
	@OneToMany(mappedBy = "power")
	private List<Lenses> lenses;
	
	public Power(int powerId, double power) {
		super();
		this.powerId = powerId;
		this.power = power;
	}
	
	public Power() {
		super();
	}

	public int getPowerId() {
		return powerId;
	}
	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}

	public List<Lenses> getLenses() {
		return lenses;
	}

	public void setLenses(List<Lenses> lenses) {
		this.lenses = lenses;
	}
	
	

	
	
	

}

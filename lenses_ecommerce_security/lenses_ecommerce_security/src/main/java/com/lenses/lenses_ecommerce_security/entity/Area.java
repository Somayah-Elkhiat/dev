package com.lenses.lenses_ecommerce_security.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String area;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ShippingFee fee;
	
	@OneToMany(mappedBy = "area")
	@JsonIgnore
	private List<UserAddress> address ;
	
	public Area() {
		super();
		
	}
	public Area(int areaId, String area) {
		super();
		this.id = areaId;
		this.area = area;
	}
	public int getAreaId() {
		return id;
	}
	public void setAreaId(int areaId) {
		this.id = areaId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public ShippingFee getFee() {
		return fee;
	}
	public void setFee(ShippingFee fee) {
		this.fee = fee;
	}
	public List<UserAddress> getAddress() {
		return address;
	}
	public void setAddress(List<UserAddress> address) {
		this.address = address;
	}
	
	
	

}

package com.lenses.lenses_ecommerce_security.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lenses.lenses_ecommerce_security.domain.User;

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class UserAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String address;
	private String mobileNumber;
	private boolean isdefault;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Area area;
	
	@Transient
	private int areaPk;
	
	@OneToMany(mappedBy="address")
	@JsonIgnore
	private List<Ordering> order;
	
	

	public UserAddress() {
		super();
	}
	
	
	public UserAddress(long addressId, String name, String mobileNumber, String address,boolean isdefault) {
		super();
		this.id = addressId;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.isdefault = isdefault;
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public boolean isIsdefault() {
		return isdefault;
	}


	public void setIsdefault(boolean isdefault) {
		this.isdefault = isdefault;
	}


	public long getAddressId() {
		return id;
	}
	public void setAddressId(long addressId) {
		this.id = addressId;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public List<Ordering> getOrder() {
		return order;
	}


	public void setOrder(List<Ordering> order) {
		this.order = order;
	}


	public int getAreaPk() {
		return areaPk;
	}


	public void setAreaPk(int areaPk) {
		this.areaPk = areaPk;
	}
	
	
	

}

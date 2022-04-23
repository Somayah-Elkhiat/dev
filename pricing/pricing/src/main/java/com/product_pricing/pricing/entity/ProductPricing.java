package com.product_pricing.pricing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductPricing {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long productId;
	private String sku;
	private double price;
	private double specialPrice;
	
	
	public ProductPricing() {
		super();
		
	}
	
	public ProductPricing(long productId, String sku, double price, double specialPrice) {
		super();
		this.productId = productId;
		this.sku = sku;
		this.price = price;
		this.specialPrice = specialPrice;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public double getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	

}

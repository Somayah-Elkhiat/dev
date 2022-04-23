package com.product_pricing.pricing.model;

public class PriceModel {
	
	private String sku;
	private double price;
	private double specialPrice;
	
	public PriceModel() {
		super();
	}
	
	public PriceModel(String sku, double price, double specialPrice) {
		super();
		this.sku = sku;
		this.price = price;
		this.specialPrice = specialPrice;
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

package com.productmodel.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductStock {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long productId;
	private String sku;
	private int quantity;
	private boolean available;
	
	
	public ProductStock() {
		super();
		
	}
	public ProductStock(long productId, String sku, int quantity, boolean available) {
		super();
		this.productId = productId;
		this.sku = sku;
		this.quantity = quantity;
		this.available= available;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	

}

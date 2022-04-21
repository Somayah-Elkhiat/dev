package com.productmodel.product.service;


public interface ProductStockService {
	int getQuantity(String sku);
	int addQuantity(String sku, int quantity);
	int removeQuantity(String sku, int quantity);
	

}

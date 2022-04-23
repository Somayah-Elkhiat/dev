package com.product_pricing.pricing.service;

import java.util.List;

import com.product_pricing.pricing.entity.ProductPricing;
import com.product_pricing.pricing.model.PriceModel;

public interface ProductPricingService {
	//double getPrice(String sku);
	//double getSpecialPrice(String sku);
	double setPrice(String sku, double price);
	double setSpecialPrice(String sku, double specialPrice);
	//List<String> getProductsOrderedByPrice();
	PriceModel getPriceModel(String sku);
	List<PriceModel> retrieveSpecificQuantityOfLensesOrederedByPrice(int number, int size);
	
}

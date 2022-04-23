package com.product_pricing.pricing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product_pricing.pricing.model.PriceModel;
import com.product_pricing.pricing.service.ProductPricingService;

@RestController
public class ProductPricingController {
	@Autowired
	ProductPricingService productPricingService;
	
	@GetMapping("/product/{sku}/price")
	public ResponseEntity<PriceModel> getPrice(@PathVariable String sku){
		PriceModel price = productPricingService.getPriceModel(sku);
		return ResponseEntity.ok().body(price);
		
	}
	
	@GetMapping("/products/ordered-by/price/page={number}/size={size}")
	public ResponseEntity<List<PriceModel>> retrieveSpecificQuantityOfProductsOrederedByPrice(@PathVariable int number,
			@PathVariable int size){
		List<PriceModel> price = productPricingService.retrieveSpecificQuantityOfLensesOrederedByPrice(number, size);
		return ResponseEntity.ok().body(price);
		
	}

	
	@PostMapping("/product/{sku}/price={price}")
	public ResponseEntity<Double> setPrice(@PathVariable String sku, @PathVariable double price){
		double newPrice = productPricingService.setSpecialPrice(sku, price);
		return ResponseEntity.ok().body(newPrice);
		
	}
	
	@PostMapping("/product/{sku}/special-price={specialPrice}")
	public ResponseEntity<Double> setSpecialPrice(@PathVariable String sku, @PathVariable double specialPrice){
		double newSpecialPrice = productPricingService.setSpecialPrice(sku, specialPrice);
		return ResponseEntity.ok().body(newSpecialPrice);
		
	}
	
	
	

	
}

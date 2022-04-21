package com.productmodel.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.productmodel.product.service.ProductStockService;

@RestController
public class ProductController {
	
	@Autowired
	ProductStockService productStockService;
	
	@GetMapping("/product/{sku}/quantity")
	public ResponseEntity<Integer> getQuantity(@PathVariable String sku){
		int productQuantity = productStockService.getQuantity(sku);
		return ResponseEntity.ok().body(productQuantity);
		
	}
	
	@PostMapping("/product/{sku}/add/quantity={quantity}")
	public ResponseEntity<Integer> addQuantity(@PathVariable String sku, @PathVariable int quantity){
		int productQuantity = productStockService.addQuantity(sku, quantity);
		return ResponseEntity.ok().body(productQuantity);	
	}
	
	@PostMapping("/product/{sku}/remove/quantity={quantity}")
	public ResponseEntity<Integer> removeQuantity(@PathVariable String sku, @PathVariable int quantity){
		int productQuantity = productStockService.removeQuantity(sku, quantity);
		return ResponseEntity.ok().body(productQuantity);	
	}
	

}

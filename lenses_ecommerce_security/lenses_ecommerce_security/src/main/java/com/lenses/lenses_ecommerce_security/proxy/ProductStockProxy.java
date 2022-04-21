package com.lenses.lenses_ecommerce_security.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="product-stock", url="localhost:8001")
public interface ProductStockProxy {
	@GetMapping("/product/{sku}/quantity")
	public ResponseEntity<Integer> getQuantity(@PathVariable String sku);
	
	@PostMapping("/product/{sku}/add/quantity={quantity}")
	public ResponseEntity<Integer> addQuantity(@PathVariable String sku, @PathVariable int quantity);
	
	@PostMapping("/product/{sku}/remove/quantity={quantity}")
	public ResponseEntity<Integer> removeQuantity(@PathVariable String sku, @PathVariable int quantity);
	

}

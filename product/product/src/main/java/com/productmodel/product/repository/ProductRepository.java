package com.productmodel.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productmodel.product.entity.ProductStock;

@Repository
public interface ProductRepository extends JpaRepository<ProductStock, Long> {
	
	ProductStock findBySku(String sku);
	

}

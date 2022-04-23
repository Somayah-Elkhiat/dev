package com.product_pricing.pricing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.product_pricing.pricing.entity.ProductPricing;


@Repository
public interface ProductPricingRepository extends JpaRepository<ProductPricing, Long>{
	ProductPricing findBySku(String sku);
	List<ProductPricing> findByOrderBySpecialPrice();

}

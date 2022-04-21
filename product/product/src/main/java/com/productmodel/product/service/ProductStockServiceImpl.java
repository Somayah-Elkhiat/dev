package com.productmodel.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productmodel.product.entity.ProductStock;
import com.productmodel.product.repository.ProductRepository;

@Service
public class ProductStockServiceImpl implements ProductStockService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public int getQuantity(String sku) {
		ProductStock product = productRepository.findBySku(sku);
		if(product == null) {
			ProductStock entity = new ProductStock();
			
			entity.setSku(sku);
			entity.setQuantity(0);
			entity.setAvailable(false);
			ProductStock newProductStock = productRepository.save(entity);
			return newProductStock.getQuantity();
			
		}else {
			if(product.isAvailable())
				return product.getQuantity();
			else
				return 0;
		}
	}

	@Override
	public int addQuantity(String sku, int quantity) {
		int oldQuantity = getQuantity(sku);
		ProductStock product = productRepository.findBySku(sku);
		product.setQuantity(oldQuantity + quantity);
		if(product.getQuantity() > 0)
			product.setAvailable(true);
		productRepository.save(product);
		return product.getQuantity();
	}

	@Override
	public int removeQuantity(String sku, int quantity) {
		int oldQuantity = getQuantity(sku);
		ProductStock product = productRepository.findBySku(sku);
		if(oldQuantity < quantity) {
			throw new RuntimeException("there is only "+ oldQuantity + " in stock" + "and you requested for "+ quantity);
		}
		else {
			product.setQuantity(oldQuantity - quantity);
			if(product.getQuantity() == 0)
				product.setAvailable(false);
		}
		productRepository.save(product);
		return product.getQuantity();
	}

}

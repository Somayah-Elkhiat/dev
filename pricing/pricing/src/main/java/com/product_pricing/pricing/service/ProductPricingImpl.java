package com.product_pricing.pricing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.product_pricing.pricing.entity.ProductPricing;
import com.product_pricing.pricing.model.PriceModel;
import com.product_pricing.pricing.repository.ProductPricingRepository;

@Service
public class ProductPricingImpl implements ProductPricingService {
	@Autowired
	ProductPricingRepository productPricingRepository;

	@Override
	public double setPrice(String sku, double price) {
		ProductPricing findBySku = productPricingRepository.findBySku(sku);
		if (findBySku == null) {
			ProductPricing save = productPricingRepository.save(new ProductPricing());
			save.setSku(sku);
			save.setPrice(price);
			;
			return save.getPrice();
		}
		findBySku.setPrice(price);
		return findBySku.getPrice();
	}

	@Override
	public double setSpecialPrice(String sku, double specialPrice) {
		ProductPricing findBySku = productPricingRepository.findBySku(sku);
		if (findBySku == null) {
			ProductPricing save = productPricingRepository.save(new ProductPricing());
			save.setSku(sku);
			save.setSpecialPrice(specialPrice);
			return save.getSpecialPrice();
		}
		findBySku.setSpecialPrice(specialPrice);
		return findBySku.getSpecialPrice();
	}

	@Override
	public PriceModel getPriceModel(String sku) {
		ProductPricing findBySku = productPricingRepository.findBySku(sku);
		if (findBySku == null) {
			ProductPricing save = productPricingRepository.save(new ProductPricing());
			save.setSku(sku);
			return new PriceModel(sku, 0, 0);
		}
		return new PriceModel(sku, findBySku.getPrice(), findBySku.getSpecialPrice());
	}

	@Override
	public List<PriceModel> retrieveSpecificQuantityOfLensesOrederedByPrice(int number, int size) {
		Pageable paging = PageRequest.of(number, size, Sort.by("specialPrice"));
		Page<ProductPricing> pageResult = productPricingRepository.findAll(paging);
		List<PriceModel> priceModelPage = new ArrayList<>();
		if (pageResult.hasContent()) {
			for (ProductPricing pricing : pageResult) {
				priceModelPage.add(new PriceModel(pricing.getSku(), pricing.getPrice(), pricing.getSpecialPrice()));

			}
			
		}
		return priceModelPage;
	}

}

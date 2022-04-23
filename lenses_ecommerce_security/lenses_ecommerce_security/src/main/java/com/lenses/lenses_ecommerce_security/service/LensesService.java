package com.lenses.lenses_ecommerce_security.service;

import java.util.List;

import com.lenses.lenses_ecommerce_security.entity.Brand;
import com.lenses.lenses_ecommerce_security.entity.Category;
import com.lenses.lenses_ecommerce_security.entity.Color;
import com.lenses.lenses_ecommerce_security.entity.Lenses;
import com.lenses.lenses_ecommerce_security.entity.Power;
import com.lenses.lenses_ecommerce_security.model.PriceModel;

public interface LensesService {
	
	List<Lenses> getAllLenses();
	Lenses getLenses(long id);
	List<Color> getAllColors();
	List<Power> getAllPower();
	List<Category> getAllCategories();
	Category getCategory(int categoryId);
	List<Brand> getAllBrands();
	Brand getBrand(int brandId);
	List<Lenses> retrieveSpecificQuantityOfLenses(int number, int size);
	Lenses saveLenses(Lenses lenses);
	void deleteLenses(long id);
	Color saveColor(Color color);
	Power savePower(Power power);
	Category saveCategory(Category category);
	Brand saveBarand(Brand brand);
	void addLensesToCategory(int categoryId, List<Long> lensesPk);
	int getQuantity(String sku);
	int addQuantity(String sku, int quantity);
	int removeQuantity(String sku, int quantity);
	PriceModel getPriceModel(String sku);
	List<Lenses> retrieveSpecificQuantityOfLensesOrederdByPrice(int number, int size);
	
	

}

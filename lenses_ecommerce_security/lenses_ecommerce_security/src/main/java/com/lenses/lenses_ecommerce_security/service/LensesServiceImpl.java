package com.lenses.lenses_ecommerce_security.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.client.RestTemplate;

import com.lenses.lenses_ecommerce_security.entity.Brand;
import com.lenses.lenses_ecommerce_security.entity.Category;
import com.lenses.lenses_ecommerce_security.entity.Color;
import com.lenses.lenses_ecommerce_security.entity.Lenses;
import com.lenses.lenses_ecommerce_security.entity.Power;
import com.lenses.lenses_ecommerce_security.model.PriceModel;
import com.lenses.lenses_ecommerce_security.proxy.ProductStockProxy;
import com.lenses.lenses_ecommerce_security.repository.BrandRepository;
import com.lenses.lenses_ecommerce_security.repository.CategoryRepository;
import com.lenses.lenses_ecommerce_security.repository.ColorRepository;
import com.lenses.lenses_ecommerce_security.repository.LensesRepository;
import com.lenses.lenses_ecommerce_security.repository.PowerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LensesServiceImpl implements LensesService {

	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private PowerRepository powerRepository;
	@Autowired
	private LensesRepository lensesRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductStockProxy productStockProxy;

	@Override
	public List<Lenses> getAllLenses() {
		return lensesRepository.findAll();
	}

	@Override
	public Lenses getLenses(long id) {
		Lenses lenses = lensesRepository.getById(id);
		if (lenses == null)
			throw new RuntimeException("Unable to find data for Lenses with id = " + id);
		return lenses;
	}

	@Override
	public List<Color> getAllColors() {

		return colorRepository.findAll();
	}

	@Override
	public List<Power> getAllPower() {
		return powerRepository.findAll();
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(int categoryId) {
		Category category = categoryRepository.getById(categoryId);
		if (category == null)
			throw new RuntimeException("Unable to find data for category with id = " + categoryId);
		return category;
	}

	@Override
	public List<Brand> getAllBrands() {
		return brandRepository.findAll();
	}

	@Override
	public Brand getBrand(int brandId) {
		Brand brand = brandRepository.getById(brandId);
		if (brand == null)
			throw new RuntimeException("Unable to find data for brand with id = " + brandId);

		return brand;
	}

	@Override
	public List<Lenses> retrieveSpecificQuantityOfLenses(int number, int size) {
		Pageable paging = PageRequest.of(number, size);
		Page<Lenses> pageResult = lensesRepository.findAll(paging);
		if (pageResult.hasContent()) {
			return pageResult.getContent();
		} else {
			return new ArrayList<Lenses>();
		}
	}

	@Override
	public Lenses saveLenses(Lenses lenses) {
		Optional<Lenses> lens = lensesRepository.findById(lenses.getId());
		if (!lens.isPresent()) { // new lenses
			lenses.setCreatedDate(new Date());

		}
		Lenses lensesBySku = lensesRepository.getBySku(lenses.getSku());
		if (lensesBySku != null)
			throw new RuntimeException("there is already lenses with sku = " + lenses.getSku());

		lenses.setModifiedDate(new Date());
		if (brandRepository.getById(lenses.getBrandPk()) == null)
			throw new RuntimeException("Unable to find data for Brand with id = " + lenses.getBrandPk());

		if (powerRepository.getById(lenses.getPowerPk()) == null)
			throw new RuntimeException("Unable to find data for Power with id = " + lenses.getPowerPk());

		if (colorRepository.getById(lenses.getColorPk()) == null)
			throw new RuntimeException("Unable to find data for Color with id = " + lenses.getColorPk());
		lenses.setColor(colorRepository.getById(lenses.getColorPk()));
		lenses.setPower(powerRepository.getById(lenses.getPowerPk()));
		lenses.setBrand(brandRepository.getById(lenses.getBrandPk()));

		return lensesRepository.save(lenses);

	}

	@Override
	public void deleteLenses(long id) {
		lensesRepository.deleteById(id);
	}

	@Override
	public Color saveColor(Color color) {
		return colorRepository.save(color);
	}

	@Override
	public Power savePower(Power power) {
		return powerRepository.save(power);
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void addLensesToCategory(int categoryId, List<Long> lensesPk) {
		int x = lensesPk.size();
		for (int i = 0; i < x; i++) {
			if (lensesRepository.findById((long) lensesPk.get(i)).isPresent()) {
				Category byId = categoryRepository.getById(categoryId);
				boolean existInCategory = false;
				for (Lenses l : byId.getLenses()) {
					if (l.getId() == (long) lensesPk.get(i))
						existInCategory = true;
				}
				if (!existInCategory) {
					byId.getLenses().add(lensesRepository.getById((long) lensesPk.get(i)));
					lensesRepository.getById((long) lensesPk.get(i)).getCategories().add(byId);
					categoryRepository.save(byId);

				}
			} else {
				continue;
			}
		}

	}

	@Override
	public Brand saveBarand(Brand brand) {

		return brandRepository.save(brand);
	}

	@Override
	public int getQuantity(String sku) {
		
		ResponseEntity<Integer> quantity = productStockProxy.getQuantity(sku);

		return quantity.getBody();
	}

	@Override
	public int addQuantity(String sku, int quantity) {
		ResponseEntity<Integer> quantityAfterAdd = productStockProxy.addQuantity(sku, quantity);

		return quantityAfterAdd.getBody();
	}

	@Override
	public int removeQuantity(String sku, int quantity) {

		ResponseEntity<Integer> quantityAfterRemove = productStockProxy.removeQuantity(sku, quantity);

		return quantityAfterRemove.getBody();
	}

	@Override
	public PriceModel getPriceModel(String sku) {
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("sku", sku);
		ResponseEntity<PriceModel> responseEntity = new RestTemplate()
				.getForEntity("http://localhost:8002/product/{sku}/price", PriceModel.class, uriVariables);

		return responseEntity.getBody();
	}

	@Override
	public List<Lenses> retrieveSpecificQuantityOfLensesOrederdByPrice(int number, int size) {
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("number", number);
		uriVariables.put("size", size);
		ResponseEntity<PriceModel[]> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8002/products/ordered-by/price/page={number}/size={size}", PriceModel[].class,
				uriVariables);
		List<Lenses> lenses = new ArrayList<>();
		for (PriceModel model : responseEntity.getBody()) {
			lenses.add(lensesRepository.getBySku(model.getSku()));

		}
		return lenses;
	}

}

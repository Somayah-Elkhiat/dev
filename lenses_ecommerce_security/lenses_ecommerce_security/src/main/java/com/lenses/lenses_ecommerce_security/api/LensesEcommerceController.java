package com.lenses.lenses_ecommerce_security.api;

import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lenses.lenses_ecommerce_security.entity.*;
import com.lenses.lenses_ecommerce_security.model.PriceModel;
import com.lenses.lenses_ecommerce_security.service.LensesService;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LensesEcommerceController {

	@Autowired
	private LensesService lensesService;

	@GetMapping("/lenses/page={number}/size={size}")//done
	public ResponseEntity<List<Lenses>> retrieveSpecificQuantityOfLenses(@PathVariable int number,
			@PathVariable int size) {

		return ResponseEntity.ok().body(lensesService.retrieveSpecificQuantityOfLenses(number, size));
	}
	
	@GetMapping("/lenses/page={number}/size={size}/ordered-by/price")//done
	public ResponseEntity<List<Lenses>> retrieveSpecificQuantityOfLensesOrederedByPrice(@PathVariable int number,
			@PathVariable int size) {
		List<Lenses> retrieveSpecificQuantityOfLenses = lensesService.retrieveSpecificQuantityOfLensesOrederdByPrice(number, size);
		return ResponseEntity.ok().body(retrieveSpecificQuantityOfLenses);
	}
	
	

	@GetMapping("/lenses") // done
	public ResponseEntity<List<Lenses>> retrieveAllLenses() {
		return ResponseEntity.ok().body(lensesService.getAllLenses());
	}
	
	@GetMapping("/product/{sku}/quantity")
	public ResponseEntity<Integer> getQuantity(@PathVariable String sku){
		int quantity = lensesService.getQuantity(sku);
		return ResponseEntity.ok().body(quantity);
		
	}
	
	

	@GetMapping("/lenses/{id}")//done
	public ResponseEntity<Lenses> retrievelensesById(@PathVariable long id) {
		return ResponseEntity.ok().body(lensesService.getLenses(id));
	}
	
	@GetMapping("/lenses/{sku}/price")
	public ResponseEntity<PriceModel> getLensesPrice(@PathVariable String sku) {
		return ResponseEntity.ok().body(lensesService.getPriceModel(sku));
	}


	@PostMapping(value = "/lenses/save", consumes = MediaType.APPLICATION_JSON_VALUE) //need to test
	public ResponseEntity<?> createLenses(@RequestBody Lenses lenses) {

		lensesService.saveLenses(lenses);
		URI uri =  URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/lenses/save").toString());

		
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("lenses/{id}/delete") 
	public ResponseEntity<?> deleteLenses(@PathVariable long id) {
		lensesService.deleteLenses(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/colors") // done
	public ResponseEntity<List<Color>> retrieveAllColors() {
		return ResponseEntity.ok().body(lensesService.getAllColors());
	}

	@PostMapping("/color/save") 
	public ResponseEntity<Color> createColor(@RequestBody Color color) {
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/color/save").toString());

		return ResponseEntity.created(uri).body(lensesService.saveColor(color));
	}


	@GetMapping("/powers") // done
	public ResponseEntity<List<Power>> retrieveAllPowers() {
		return ResponseEntity.ok().body(lensesService.getAllPower());
	}

	@PostMapping("/power/save") 
	public ResponseEntity<Power> createPower(@RequestBody Power power) {
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/power/save").toString());

		return ResponseEntity.created(uri).body(lensesService.savePower(power));
	}


	@GetMapping("/categories") // done
	public ResponseEntity<List<Category>> retrieveAllCategories() {
		return ResponseEntity.ok().body(lensesService.getAllCategories());
	}

	@GetMapping(path = "/categories/{categoryId}")//done
	public ResponseEntity<Category> retrieveCategoryById(@PathVariable int categoryId) {
		return ResponseEntity.ok().body(lensesService.getCategory(categoryId));
	}

	@PostMapping(value = "/category/save") 
	public ResponseEntity<Object> createCategory(@RequestBody Category category) {
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/category/save").toString());
		return ResponseEntity.created(uri).body(lensesService.saveCategory(category));
	}

	@RequestMapping(value = "/lenses-to-category/{categoryId}", method = RequestMethod.PUT)
	public ResponseEntity<?> addLensesToCategory(@PathVariable int categoryId, @RequestBody List<Long> lensesPk) {
		lensesService.addLensesToCategory(categoryId, lensesPk);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/brand/{brandId}")//done
	public ResponseEntity<Brand> retrieveBrandById(@PathVariable int brandId) {
		return ResponseEntity.ok().body(lensesService.getBrand(brandId));
	}

	@GetMapping("/brands")//done
	public ResponseEntity<List<Brand>> retrieveAllBrands() {
		return ResponseEntity.ok().body(lensesService.getAllBrands());

	}

	@PostMapping("/brand/save") 
	public ResponseEntity<Object> createBrand(@RequestBody Brand brand) {
		URI uri = URI.create(ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/brand/save").toString());
		

		return ResponseEntity.created(uri).build();
	}
	
	
	/*@GetMapping("/lenses/ordered-by-price") // done
	public List<Lenses> retrieveAllLensesOrderedByPrice() {
		List<Lenses> lenses = lensesRepository.findByOrderBySpecialPrice();
		return lenses;
	}*/
	
	/*@RequestMapping(value = "/colors/{id}", method = RequestMethod.PUT) 
	public void updateColor(@PathVariable int id, @RequestBody Color color) {
		color.setColorId(id);
		Color c = colorRepository.getById(id);
		if (c == null)
			throw new RuntimeException("Unable to find data for color with id = " + color.getColorId());

		colorRepository.save(color);
	}*/
	
	/*	@RequestMapping(value = "/lenses/{id}", method = RequestMethod.PUT) // done
	public ResponseEntity<Lenses> saveLenses(@PathVariable int id, @RequestBody Lenses lenses) {

		if (brandRepository.getById(lenses.getBrandPk()) == null)
			throw new RuntimeException("Unable to find data for Brand with id = " + lenses.getBrandPk());

		if (powerRepository.getById(lenses.getPowerPk()) == null)
			throw new RuntimeException("Unable to find data for Power with id = " + lenses.getPowerPk());

		Optional<Color> color = colorRepository.findById(lenses.getColorPk());
		if (color == null)
			throw new RuntimeException("Unable to find data for Color with id = " + lenses.getColorPk());
		lenses.setId(id);
		Lenses lens = lensesRepository.getById(id);
		if (lens == null)
			throw new RuntimeException("Unable to find data for lenses with id = " + lenses.getId());

		String sku = lens.getSku();
		if (lenses.getSku() != sku) {
			Lenses le = lensesRepository.getBySku(lenses.getSku());
			if (le != null)
				lenses.setSku(sku);
		}
		lenses.setColor(colorRepository.getById(lenses.getColorPk()));
		lenses.setPower(powerRepository.getById(lenses.getPowerPk()));
		lenses.setBrand(brandRepository.getById(lenses.getBrandPk()));

		lensesRepository.save(lenses);
	}
*/
	
	/*@RequestMapping(value = "/powers/{id}", method = RequestMethod.PUT) 
	public Power updatePower(@PathVariable int id, @RequestBody Power power) {
		power.setPowerId(id);
		Power p = powerRepository.getById(id);
		if (p == null)
			throw new RuntimeException("Unable to find data for power with id = " + power.getPowerId());

		return powerRepository.save(power);
	}*/
	
	/*@RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT) 
	public Category createCategory(@PathVariable int id, @RequestBody Category category) {
		category.setId(id);
		Category c = categoryRepository.getById(id);
		if (c == null)
			throw new RuntimeException("Unable to find data for category with id = " + category.getId());

		return categoryRepository.save(category);
	}*/

}

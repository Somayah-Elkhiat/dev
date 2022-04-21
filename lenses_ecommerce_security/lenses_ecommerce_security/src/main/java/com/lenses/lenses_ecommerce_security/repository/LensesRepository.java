package com.lenses.lenses_ecommerce_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lenses.lenses_ecommerce_security.entity.Lenses;


@Repository
public interface LensesRepository extends JpaRepository<Lenses, Long>{
	
	Lenses getBySku(String sku);
	//List<Lenses> findByOrderBySpecialPrice();
	

}

package com.lenses.lenses_ecommerce_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lenses.lenses_ecommerce_security.entity.Power;




@Repository
public interface PowerRepository extends JpaRepository<Power, Integer> {

}

package com.lenses.lenses_ecommerce_security.repository;

import com.lenses.lenses_ecommerce_security.domain.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}

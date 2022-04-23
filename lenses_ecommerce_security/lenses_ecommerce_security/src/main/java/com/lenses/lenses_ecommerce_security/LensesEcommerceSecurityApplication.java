package com.lenses.lenses_ecommerce_security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenses.lenses_ecommerce_security.domain.Role;
import com.lenses.lenses_ecommerce_security.domain.User;
import com.lenses.lenses_ecommerce_security.model.ReviewRequestModel;
import com.lenses.lenses_ecommerce_security.service.UserService;
@EnableFeignClients
@SpringBootApplication
public class LensesEcommerceSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LensesEcommerceSecurityApplication.class, args);
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	CommandLineRunner run(UserService userService) {
		return args ->{
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			
			userService.saveUser(new User(null, "Sarah AK", "sarah", "12345", "sarah@ak",new ArrayList<>()));
			userService.saveUser(new User(null, "admin ad", "admin", "12345", "somayahkhaled@yahoo.com",new ArrayList<>()));
			
			userService.addRoleToUser("sarah", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_ADMIN");
		};
		
	}
	@Autowired
	ObjectMapper objectMapper;
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args ->{
			
			kafkaTemplate.send("order", "Hello :)");
		};
		
	}
	

}

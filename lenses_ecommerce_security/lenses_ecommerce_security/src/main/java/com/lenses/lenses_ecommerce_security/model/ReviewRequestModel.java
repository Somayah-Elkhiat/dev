package com.lenses.lenses_ecommerce_security.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ReviewRequestModel implements Serializable{
	private static final long serialVersionUID = 123L;
	private long orderId;
	private List<String> sku;
	private long userId;
	private String email;
	
	
	
	
	
	

}

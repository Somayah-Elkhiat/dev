package com.reviews.reviews.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
public class ReviewRequestModel {

	private List<String> sku;
	private long userId;
	private String email;







}

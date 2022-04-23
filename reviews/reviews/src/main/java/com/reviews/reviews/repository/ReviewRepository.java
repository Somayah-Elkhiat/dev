package com.reviews.reviews.repository;
import com.reviews.reviews.entity.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ReviewRepository extends JpaRepository<Review, Long>{
	List<Review> findReviewsBySku(String sku);
}

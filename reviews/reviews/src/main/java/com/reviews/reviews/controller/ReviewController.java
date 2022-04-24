package com.reviews.reviews.controller;

import com.reviews.reviews.entity.Review;
import com.reviews.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;

    @PostMapping("/review/item={sku}/userId={userId}")
    public ResponseEntity<Review> postReview(@RequestBody Review review, @PathVariable String sku, @PathVariable long userId){
        review.setSku(sku);
        review.setUserId(userId);
        Review savedReview = reviewRepository.save(review);
        return ResponseEntity.ok().body(savedReview);
    }
}

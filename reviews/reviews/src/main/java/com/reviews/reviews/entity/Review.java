package com.reviews.reviews.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long reviewId;
	private String sku;
	private long userId;
	private String commentHeader;
	private String comment;
	private int rate;

	
	public Review() {
		super();
	
	}
	public Review(long reviewId, String sku, long userId, String commentHeader, String comment, int rate) {
		super();
		this.reviewId = reviewId;
		this.sku = sku;
		this.userId = userId;
		this.commentHeader = commentHeader;
		this.comment = comment;
		this.rate = rate;
	}
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getCommentHeader() {
		return commentHeader;
	}
	public void setCommentHeader(String commentHeader) {
		this.commentHeader = commentHeader;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	
	

}

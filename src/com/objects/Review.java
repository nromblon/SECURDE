package com.objects;

public class Review {
	
	private String username;
	private String reviewText;
	public Review(String username, String reviewText) {
		super();
		this.username = username;
		this.reviewText = reviewText;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

}

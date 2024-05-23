package com.example.demo.shop.application.dto;

import com.example.demo.shop.domain.Review;

public record ReviewInfoResponse(
	String username,
	String reviewMessage
) {

	public static ReviewInfoResponse of(Review review) {
		return new ReviewInfoResponse(
			review.getUsername(),
			review.getReviewMessage()
		);
	}
}

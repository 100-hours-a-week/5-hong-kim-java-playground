package com.example.demo.shop.application;

import static com.example.demo.shop.exception.ReviewExceptionType.*;

import java.util.List;
import java.util.Optional;

import com.example.demo.async.EventProducer;
import com.example.demo.member.domain.Member;
import com.example.demo.shop.application.dto.ReviewInfoResponse;
import com.example.demo.shop.domain.Review;
import com.example.demo.shop.domain.repository.ReviewRepository;
import com.example.demo.shop.exception.ReviewException;

public class ReviewService {

	private final ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public void createOrUpdateReview(Member member, String message) {
		String username = member.getUsername();

		Optional<Review> optionalReview = reviewRepository.findByUsername(username);

		Review review = optionalReview
			.map(existingReview -> {
				existingReview.updateReviewMessage(message);  // 기존 리뷰 수정
				return existingReview;
			})
			.orElseGet(() -> new Review.Builder()  // 존재하지 않을 경우 새 리뷰 생성
				.username(username)
				.reviewMessage(message)
				.build());

		// reviewRepository.save(review);
		EventProducer.publish(review);
	}

	public ReviewInfoResponse showOwnReview(Member member) {
		String username = member.getUsername();

		Review review = reviewRepository.findByUsername(username)
			.orElseThrow(() -> new ReviewException(NOT_EXIST_REVIEW));

		return ReviewInfoResponse.of(review);
	}

	public List<ReviewInfoResponse> showAllReviews() {
		List<Review> reviews = reviewRepository.findAll()
			.orElseThrow(() -> new ReviewException(NOT_EXIST_REVIEW));

		return reviews.stream()
			.map(ReviewInfoResponse::of)
			.toList();
	}
}

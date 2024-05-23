package com.example.demo.shop.presentation;

import java.util.List;
import java.util.Optional;

import com.example.demo.common.utils.InputUtils;
import com.example.demo.member.domain.Member;
import com.example.demo.shop.application.ReviewService;
import com.example.demo.shop.application.dto.ReviewInfoResponse;
import com.example.demo.shop.exception.ReviewException;

public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	public void writeReview(Member member) {
		System.out.println("가게에 대한 리뷰를 작성해주세요.");
		System.out.print("리뷰 : ");
		String reviewMessage = InputUtils.getStringInput();

		reviewService.createOrUpdateReview(member, reviewMessage);
	}

	public void showOwnReview(Member member) {
		getReviewInfo(member).ifPresent(review -> {
			System.out.println(review.username() + "님이 작성하신 리뷰 입니다.");
			System.out.println("| >> 내용 : " + review.reviewMessage());
		});
	}

	public void showAllReview() {
		getReviewInfoList().ifPresent(reviews -> {
			for (ReviewInfoResponse review : reviews) {
				System.out.println(review.username() + "님이 작성하신 리뷰 입니다.");
				System.out.println("| >> 내용 : " + review.reviewMessage());
				System.out.println("| ---------------------------");
			}
		});
	}

	private Optional<ReviewInfoResponse> getReviewInfo(Member member) {
		try {
			return Optional.ofNullable(reviewService.showOwnReview(member));
		} catch (ReviewException ex) {
			System.out.println(ex.getMessage());
			return Optional.empty();
		}
	}

	private Optional<List<ReviewInfoResponse>> getReviewInfoList() {
		try {
			return Optional.ofNullable(reviewService.showAllReviews());
		} catch (ReviewException ex) {
			System.out.println(ex.getMessage());
			return Optional.empty();
		}
	}
}

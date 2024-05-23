package com.example.demo.shop.domain;

import java.time.LocalDateTime;

import com.example.demo.common.domain.BaseTimeEntity;

public class Review extends BaseTimeEntity {

	private String username;
	private String reviewMessage;

	public Review(LocalDateTime createdAt, String username, String reviewMessage) {
		super(createdAt);
		this.username = username;
		this.reviewMessage = reviewMessage;
	}

	public String getReviewMessage() {
		return reviewMessage;
	}

	public String getUsername() {
		return username;
	}

	public void updateReviewMessage(String reviewMessage) {
		this.reviewMessage = reviewMessage;
	}

	public static class Builder {
		private String username;
		private String reviewMessage;

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder reviewMessage(String reviewMessage) {
			this.reviewMessage = reviewMessage;
			return this;
		}

		public Review build() {
			return new Review(LocalDateTime.now(), username, reviewMessage);
		}
	}
}

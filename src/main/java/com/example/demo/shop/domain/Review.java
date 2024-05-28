package com.example.demo.shop.domain;

import java.time.LocalDateTime;

import com.example.demo.common.domain.BaseTimeEntity;
import com.example.demo.common.utils.FormatUtils;

public class Review implements BaseTimeEntity {

	private LocalDateTime createdAt;
	private String username;
	private String reviewMessage;

	public Review(String username, String reviewMessage) {
		createdAt = LocalDateTime.now();
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

	@Override
	public String getCreatedTimeToString() {
		return FormatUtils.formatDateTime(createdAt);
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
			return new Review(username, reviewMessage);
		}
	}
}

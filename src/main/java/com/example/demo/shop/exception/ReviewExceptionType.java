package com.example.demo.shop.exception;

import com.example.demo.common.exception.BaseExceptionType;

public enum ReviewExceptionType implements BaseExceptionType {
	NOT_EXIST_REVIEW("존재하는 리뷰가 없습니다"),
	;

	private final String message;

	ReviewExceptionType(String message) {
		this.message = message;
	}

	@Override
	public String message() {
		return message;
	}
}

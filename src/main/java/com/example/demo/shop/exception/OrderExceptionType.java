package com.example.demo.shop.exception;

import com.example.demo.common.exception.BaseExceptionType;

public enum OrderExceptionType implements BaseExceptionType {
	INSUFFICIENT_BALANCE("잔액이 부족합니다."),
	NOT_FOUND_ORDER_INFO("일치하는 주문 정보가 없습니다."),
	;

	private final String message;

	OrderExceptionType(String message) {
		this.message = message;
	}

	@Override
	public String message() {
		return message;
	}
}

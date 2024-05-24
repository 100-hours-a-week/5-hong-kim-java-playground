package com.example.demo.shop.exception;

import com.example.demo.common.exception.BaseException;

public class OrderException extends BaseException {

	public OrderException(OrderExceptionType exceptionType) {
		super(exceptionType);
	}
}

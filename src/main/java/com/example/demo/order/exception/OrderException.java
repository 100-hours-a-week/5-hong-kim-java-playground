package com.example.demo.order.exception;

import com.example.demo.common.exception.BaseException;
import com.example.demo.common.exception.BaseExceptionType;

public class OrderException extends BaseException {

	public OrderException(BaseExceptionType baseExceptionType) {
		super(baseExceptionType);
	}
}

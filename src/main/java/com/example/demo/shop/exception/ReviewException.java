package com.example.demo.shop.exception;

import com.example.demo.common.exception.BaseException;

public class ReviewException extends BaseException {

	public ReviewException(ReviewExceptionType exceptionType) {
		super(exceptionType);
	}
}

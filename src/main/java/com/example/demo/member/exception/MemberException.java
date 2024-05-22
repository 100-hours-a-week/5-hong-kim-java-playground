package com.example.demo.member.exception;

import com.example.demo.common.exception.BaseException;

public class MemberException extends BaseException {

	public MemberException(MemberExceptionType exceptionType) {
		super(exceptionType);
	}
}

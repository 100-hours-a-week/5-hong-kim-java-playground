package com.example.demo.member.exception;

import com.example.demo.common.exception.BaseExceptionType;

public enum MemberExceptionType implements BaseExceptionType {
	DUPLICATE_USERNAME("중복된 사용자 이름입니다"),
	NOT_EXIST_MEMBER("존재하지 않는 회원입니다"),
	MISMATCH_PASSWORD("일치하지 않은 비밀번호 입니다"),
	INVALID_MEMBER_TYPE("유효하지 않은 회원 타입입니다"),
	UN_AUTHORIZATION_MEMBER("로그인 정보가 없습니다"),
	INSUFFICIENT_BALANCE("잔액이 부족합니다."),
	;

	private final String message;

	MemberExceptionType(String message) {
		this.message = message;
	}

	@Override
	public String message() {
		return message;
	}
}

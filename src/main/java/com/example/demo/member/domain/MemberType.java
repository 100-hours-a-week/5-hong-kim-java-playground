package com.example.demo.member.domain;

import java.util.Arrays;
import java.util.Optional;

public enum MemberType {
	CUSTOMER("고객", 0L),
	ADMIN("관리자", 999_999_999L),
	;

	private final String name;
	private final Long balance;

	MemberType(String name, Long balance) {
		this.name = name;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public Long getBalance() {
		return balance;
	}

	public static Optional<MemberType> getMemberTypeByName(String name) {
		return Arrays.stream(MemberType.values())
			.filter(memberType -> memberType.getName().equals(name))
			.findFirst();
	}
}

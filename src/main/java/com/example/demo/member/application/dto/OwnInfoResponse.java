package com.example.demo.member.application.dto;

import com.example.demo.member.domain.Member;

public record OwnInfoResponse(
	String username,
	String createdAt,
	Long balance
) {

	public static OwnInfoResponse of(Member member) {
		return new OwnInfoResponse(
			member.getUsername(),
			member.getCreatedTimeToString(),
			member.getBalance()
		);
	}
}

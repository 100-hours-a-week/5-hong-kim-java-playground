package com.example.demo.member.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import com.example.demo.common.domain.BaseTimeEntity;
import com.example.demo.common.utils.FormatUtils;

public class Member implements BaseTimeEntity {

	private LocalDateTime createdAt;
	private String username;
	private String password;
	private Long balance;
	private MemberType memberType;

	public Member(String username, String password, MemberType memberType) {
		createdAt = LocalDateTime.now();
		this.username = username;
		this.password = password;
		this.balance = memberType.getBalance();
		this.memberType = memberType;
	}

	public String getUsername() {
		return username;
	}

	public Long getBalance() {
		return balance;
	}

	public void increaseBalance(Long balance) {
		this.balance += balance;
	}

	public void decreaseBalance(Long balance) {
		this.balance -= balance;
	}

	public MemberType getPlayerType() {
		return memberType;
	}

	public boolean isMatchPassword(String password) {
		return Objects.equals(this.password, password);
	}

	@Override
	public String getCreatedTimeToString() {
		return FormatUtils.formatDateTime(createdAt);
	}

	public static class Builder {
		private String username;
		private String password;
		private MemberType memberType;

		public Builder() {
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder memberType(MemberType memberType) {
			this.memberType = memberType;
			return this;
		}

		public Member build() {
			return new Member(username, password, memberType);
		}
	}
}

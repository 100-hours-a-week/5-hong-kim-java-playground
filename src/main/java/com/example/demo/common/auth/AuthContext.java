package com.example.demo.common.auth;

import java.util.Optional;

import com.example.demo.member.domain.Member;

// Initialization on demand holder idiom 방법으로 Singleton 구현
public class AuthContext {

	private AuthContext() {  // 인스턴스화 방지
	}

	private static class MemberContextHolder {
		private static final AuthContext INSTANCE = new AuthContext();
	}

	public static AuthContext getInstance() {
		return MemberContextHolder.INSTANCE;
	}

	private Member currentMember;

	public synchronized void setCurrentMember(Member member) {
		this.currentMember = member;
	}

	public synchronized Optional<Member> getCurrentMember() {
		return Optional.ofNullable(currentMember);
	}
}

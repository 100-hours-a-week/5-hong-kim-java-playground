package com.example.demo.member;

import com.example.demo.member.application.MemberService;
import com.example.demo.member.domain.repository.MemberRepository;
import com.example.demo.member.infrastructure.InMemoryMemberRepository;
import com.example.demo.member.presentation.MemberController;
import com.example.demo.member.presentation.MemberFacadeController;

// Initialization on demand holder idiom 방법으로 Singleton 구현
public class MemberContainer {

	private MemberContainer() {  // 인스턴스화 방지
	}

	private static class MemberRepositoryHolder {
		private static final MemberRepository INSTANCE = new InMemoryMemberRepository();
	}

	public static MemberRepository memberRepository() {
		return MemberRepositoryHolder.INSTANCE;
	}

	private static class MemberServiceHolder {
		private static final MemberService INSTANCE = new MemberService(memberRepository());
	}

	public static MemberService memberService() {
		return MemberServiceHolder.INSTANCE;
	}

	private static class MemberControllerHolder {
		private static final MemberController INSTANCE = new MemberController(memberService());
	}

	public static MemberController memberController() {
		return MemberControllerHolder.INSTANCE;
	}

	private static class MemberFacadeControllerHolder {
		private static final MemberFacadeController INSTANCE = new MemberFacadeController(memberController());
	}

	public static MemberFacadeController memberFacadeController() {
		return MemberFacadeControllerHolder.INSTANCE;
	}
}

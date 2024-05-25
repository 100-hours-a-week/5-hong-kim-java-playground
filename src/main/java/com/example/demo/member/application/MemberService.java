package com.example.demo.member.application;

import static com.example.demo.member.exception.MemberExceptionType.*;

import com.example.demo.common.auth.AuthContext;
import com.example.demo.member.application.dto.OwnInfoResponse;
import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.MemberType;
import com.example.demo.member.domain.repository.MemberRepository;
import com.example.demo.member.exception.MemberException;

// TODO: 예외처리 세분화
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void createNewMember(String username, String password, String joinType) {
		memberRepository.findByUsername(username)
			.ifPresent((member) -> {
				throw new MemberException(DUPLICATE_USERNAME);
			});

		MemberType memberType = MemberType.getMemberTypeByName(joinType)
			.orElseThrow(() -> new MemberException(INVALID_MEMBER_TYPE));

		Member member = new Member.Builder()
			.username(username)
			.password(password)
			.memberType(memberType)
			.build();

		memberRepository.save(member);
	}

	public void loginMember(String username, String password) {
		Member member = memberRepository.findByUsername(username)
			.orElseThrow(() -> new MemberException(NOT_EXIST_MEMBER));

		if (!member.isMatchPassword(password)) {
			throw new MemberException(MISMATCH_PASSWORD);
		}

		AuthContext.getInstance().setCurrentMember(member);
	}

	public void rechargeBalance(Long balance) {
		Member member = getCurrentMember();
		member.increaseBalance(balance);
	}

	public void processPayment(Long balance) {
		Member member = getCurrentMember();

		Long currentBalance = member.getBalance();
		if (currentBalance < balance) {
			throw new MemberException(INSUFFICIENT_BALANCE);
		}

		member.decreaseBalance(balance);
	}

	public Long getCurrentMemberBalance() {
		Member member = getCurrentMember();
		return member.getBalance();
	}

	public OwnInfoResponse getCurrentMemberInfo() {
		Member member = getCurrentMember();
		return OwnInfoResponse.of(member);
	}

	private Member getCurrentMember() {
		return AuthContext.getInstance().getCurrentMember()
			.orElseThrow(() -> new MemberException(UN_AUTHORIZATION_MEMBER));
	}
}

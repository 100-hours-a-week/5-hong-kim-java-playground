package com.example.demo.member.application;

import com.example.demo.common.auth.AuthContext;
import com.example.demo.member.application.dto.OwnInfoResponse;
import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.MemberType;
import com.example.demo.member.domain.repository.MemberRepository;

// TODO: 예외처리 세분화
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void createNewMember(String username, String password, String joinType) {
		memberRepository.findByUsername(username)
			.ifPresent((member) -> {
				throw new RuntimeException("중복된 이름");
			});

		MemberType memberType = MemberType.getMemberTypeByName(joinType)
			.orElseThrow(RuntimeException::new);

		Member member = new Member.Builder()
			.username(username)
			.password(password)
			.memberType(memberType)
			.build();

		memberRepository.save(member);
	}

	public void loginMember(String username, String password) {
		Member member = memberRepository.findByUsername(username)
			.orElseThrow(RuntimeException::new);

		if (!member.isMatchPassword(password)) {
			throw new RuntimeException("비밀번호가 다름");
		}

		AuthContext.getInstance().setCurrentMember(member);
	}

	public void rechargeBalance(Long balance) {
		Member member = AuthContext.getInstance().getCurrentMember()
			.orElseThrow(RuntimeException::new);
		member.increaseBalance(balance);
	}

	public void processPayment(Long balance) {
		Member member = AuthContext.getInstance().getCurrentMember()
			.orElseThrow(RuntimeException::new);
		member.decreaseBalance(balance);
	}

	public Long getCurrentMemberBalance() {
		Member member = AuthContext.getInstance().getCurrentMember()
			.orElseThrow(RuntimeException::new);
		return member.getBalance();
	}

	public OwnInfoResponse getCurrentMemberInfo() {
		Member member = AuthContext.getInstance().getCurrentMember()
			.orElseThrow(RuntimeException::new);
		return OwnInfoResponse.of(member);
	}
}

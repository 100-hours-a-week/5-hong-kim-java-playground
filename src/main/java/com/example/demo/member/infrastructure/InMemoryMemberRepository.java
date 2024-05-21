package com.example.demo.member.infrastructure;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.repository.MemberRepository;

public class InMemoryMemberRepository implements MemberRepository {

	private final Map<String, Member> memberMap = new ConcurrentHashMap<>();

	@Override
	public void save(Member member) {
		String username = member.getUsername();
		memberMap.put(username, member);
	}

	@Override
	public Optional<Member> findByUsername(String username) {
		return Optional.ofNullable(memberMap.get(username));
	}
}

package com.example.demo.member.domain.repository;

import java.util.Optional;

import com.example.demo.member.domain.Member;

public interface MemberRepository {

	void save(Member member);

	Optional<Member> findByUsername(String username);
}

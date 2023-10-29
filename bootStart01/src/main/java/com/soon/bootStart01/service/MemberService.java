package com.soon.bootStart01.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soon.bootStart01.entity.Member;
import com.soon.bootStart01.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Member create(String username, String email, String password) {
		Member member = new Member();
		member.setUsername(username);
		member.setEmail(email);
		member.setPassword(passwordEncoder.encode(password));
		this.memberRepository.save(member);
		return member;
	}

}

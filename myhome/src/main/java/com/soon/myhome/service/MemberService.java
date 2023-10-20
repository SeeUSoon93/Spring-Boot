package com.soon.myhome.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soon.myhome.entity.Member;
import com.soon.myhome.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder pwEncoder;
	
	public Member join(String memId, String memPw, String memEmail) {
		Member member = new Member();
		member.setMemId(memId);
		
		// BCryptPasswordEncoder - BCrypt 해싱 함수를 사용해서 비밀번호를 암호화
		// BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		// BCryptPasswordEncoder 객체를 직접 생성하여 사용하지 않고 PasswordEncoder 객체를 주입받아 사용
		member.setMemPw(pwEncoder.encode(memPw));
		
		member.setMemEmail(memEmail);
		this.memberRepository.save(member);
		return member;
		
	}

}

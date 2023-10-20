package com.soon.myhome.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soon.myhome.form.MemberForm;
import com.soon.myhome.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/join")
	public String login(MemberForm memberForm) {
		return "join";
	}
	
	@PostMapping("/join")
	public String login(@Valid MemberForm memberForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "join";
		}
		
		// pw1과pw2가 일치하는지 검증
		// bindingResult.rejectValue(필드명, 오류코드, 에러메세지) - 일치하지 않을 경우에 오류 발생하도록
		if(!memberForm.getMemPw1().equals(memberForm.getMemPw2())) {
			bindingResult.rejectValue("memPw2", "PwNotSame","비밀번호가 일치하지 않습니다.");
			return "join";
		}
		try {
		memberService.join(memberForm.getMemId(), memberForm.getMemPw1(),memberForm.getMemEmail());
		}catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.rejectValue("joinFail", "이미 가입되었습니다.");
			return "join";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.rejectValue("joinFail", e.getMessage());
			return "join";			
		}
		
		return "redirect:/";
	}

}

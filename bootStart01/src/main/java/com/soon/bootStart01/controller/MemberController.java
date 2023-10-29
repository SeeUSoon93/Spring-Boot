package com.soon.bootStart01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soon.bootStart01.form.MemberForm;
import com.soon.bootStart01.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/signup")
	public String signup(MemberForm memberForm) {
		return "signup_form";
	}

	@PostMapping("/signup")
	public String signup(@Valid MemberForm memberForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		if (!memberForm.getPassword1().equals(memberForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordIncorrect", "비밀번호를 확인해주세요!");
			return "signup_form";
		}
		try {
			this.memberService.create(memberForm.getUsername(), memberForm.getPassword1(), memberForm.getEmail());
		} catch (Exception e) {
			bindingResult.reject("signupfail", "이미 등록된 유저입니다.");
			return "signup_form";
		}
		return "redirect:/main";
	}

	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
}

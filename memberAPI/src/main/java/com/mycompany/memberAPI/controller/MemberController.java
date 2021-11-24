package com.mycompany.memberAPI.controller;

import javax.annotation.Resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.Member;
import com.mycompany.memberAPI.service.MemberService;
import com.mycompany.memberAPI.service.MemberService.JoinResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Resource
	private PasswordEncoder passwordEncoder;
	
	@Resource
	private MemberService memberService;
	
	@PostMapping
	public String insertMember(@RequestBody Member member) {
		log.info("회원가입 실행");
		
		//비밀번호 암호화
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		//회원 권한 부여
		member.setMemberRole("ROLE_USER");
		
		//중복 가입 여부에 따른 처리 결과 반환
		JoinResult jr = memberService.joinMember(member);
		
		String result;
		if(jr == JoinResult.SUCCESS) {
			result = "success";
			log.info("회원가입 성공");
		} else if(jr == JoinResult.DUPLICATED) {
			result = "duplicated";
			log.info("중복된 아이디");
		} else {
			result = "fail";
			log.info("회원가입 실패");
		}
		
		return result;
	}
	
	
	
	
}

package com.mycompany.memberAPI.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.Member;
import com.mycompany.memberAPI.dto.MemberForOrder;
import com.mycompany.memberAPI.security.JwtUtil;
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
	
	@Resource
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/join")
	public String joinMember(@RequestBody Member member) {
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
		} else if(jr == JoinResult.DUPLICATE) {
			result = "duplicate";
			log.info("중복된 아이디");
		} else {
			result = "fail";
			log.info("회원가입 실패");
		}
		
		return result;
	}
	
	@PatchMapping("/delete/{memberId}")
	public void deleteMember(@PathVariable String memberId) {
		log.info("회원탈퇴 실행");
		memberService.deleteMember(memberId);
	}
	
	@PatchMapping("/login")
	public Map<String, String> login(@RequestBody Member member) {
		log.info("로그인 실행");
		
		String memberId = member.getMemberId();
		String password = member.getPassword();
		
		Map<String, String> map = new HashMap<>();
		
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(memberId, password);
			Authentication authentication = authenticationManager.authenticate(token); //아이디, 비밀번호 인증 여부 확인 후 객체 생성
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
		
			//최근 로그인 날짜 업데이트
			memberService.updateLastLoginDate(memberId);
			
			String authority = authentication.getAuthorities().iterator().next().toString();
			map.put("result", "success");
			map.put("memberId", memberId);
			map.put("jwt", JwtUtil.createToken(memberId, authority));
		} catch (DisabledException de) {
			map.put("result", "disabledMember");
		}
		
		return map;
	}
	
	@GetMapping("/{memberId}")
	public Member getMember(@PathVariable String memberId) {
		log.info("회원정보 조회");
		return memberService.getMember(memberId);
	}
	
	@PatchMapping
	public void updateMember(@RequestBody Member member) {
		log.info("회원정보 수정");
		memberService.updateMember(member);
	}
	
	@GetMapping("/order")
	public MemberForOrder getMemberForOrder(@RequestBody String memberId) {
		log.info("주문을 위한 회원정보 조회");
		return memberService.getMemberForOrder(memberId);
	}
	
}

package com.mycompany.memberAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.MemberCoupon;
import com.mycompany.memberAPI.service.MemberCouponService;
import com.mycompany.memberAPI.service.MemberCouponService.InsertMemberCouponResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/member/coupon")
public class MemberCouponContoller {
	
	@Resource
	private MemberCouponService memberCouponService;
	
	@GetMapping("/availableCoupon")
	public List<MemberCoupon> getAvailableMemberCoupon(@RequestBody String memberId) {
		log.info("사용 가능한 쿠폰 목록 조회 실행");
		return memberCouponService.getAvailableMemberCoupon(memberId);
	}
	
	@GetMapping("/unavailableCoupon")
	public List<MemberCoupon> getUnavailableMemberCoupon(@RequestBody String memberId) {
		log.info("사용 불가능한(사용완료/기간만료) 쿠폰 목록 조회 실행");
		return memberCouponService.getUnavailableMemberCoupon(memberId);
	}
	
	@PostMapping
	public InsertMemberCouponResult insertMemberCoupon(@RequestBody MemberCoupon memberCoupon) {
		log.info("쿠폰 발급 실행");
		InsertMemberCouponResult insertMemberCouponResult = memberCouponService.insertMemberCoupon(memberCoupon);
		return insertMemberCouponResult;
	}
	
	@PatchMapping
	public void updateMemberCoupon(@RequestBody MemberCoupon memberCoupon) {
		log.info("쿠폰 사용 실행");
		memberCouponService.updateMemberCoupon(memberCoupon);
	}
	
}
package com.mycompany.memberAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.Point;
import com.mycompany.memberAPI.service.MemberService;
import com.mycompany.memberAPI.service.PointService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/point")
public class PointController {
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private PointService pointService;
	
	//포인트잔액 조회
	@GetMapping("/{memberId}")
	public int getMemberPoint(@PathVariable String memberId) {
		log.info("포인트 잔액 조회 실행");
		return memberService.getMemberPoint(memberId);
	}
	
	//포인트내역 조회
	@GetMapping("/list/{memberId}")
	public List<Point> getPointList(@PathVariable String memberId){
		log.info("포인트내역 조회 실행");
		List<Point> pointList = pointService.getPointList(memberId);
		log.info(pointList.toString());
		return pointList;
	}
	
	//포인트 적립(추가)
	@PostMapping("/save")
	public void insertSavePoint(@RequestBody Point savePoint) {
		log.info("포인트 적립 실행");
		pointService.insertSavePoint(savePoint);
	}
	
	//포인트 사용(추가)
	@PostMapping("/use")
	public void insertUsePoint(@RequestBody Point usePoint) {
		log.info("포인트 사용 실행");
		pointService.insertUsePoint(usePoint);
	}
	
	//포인트 환불(추가)
	@PostMapping("/refund")
	public void insertRefundPoint(@RequestBody Point refundPoint) {
		log.info("포인트 환불 실행");
		log.info(refundPoint.toString());
		pointService.insertRefundPoint(refundPoint);
	}
}

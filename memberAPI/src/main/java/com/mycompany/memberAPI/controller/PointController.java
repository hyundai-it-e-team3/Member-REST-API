package com.mycompany.memberAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.PointList;
import com.mycompany.memberAPI.dto.SavePoint;
import com.mycompany.memberAPI.dto.UsePoint;
import com.mycompany.memberAPI.service.MemberService;
import com.mycompany.memberAPI.service.PointListService;
import com.mycompany.memberAPI.service.SavePointService;
import com.mycompany.memberAPI.service.UsePointService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/point")
public class PointController {
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private PointListService pointListService;
	
	@Resource
	private SavePointService savePointService;
	
	@Resource
	private UsePointService usePointService;
	
	//포인트잔액 조회
	@GetMapping
	public int getMemberPoint(@RequestBody String memberId) {
		log.info("포인트 잔액 조회 실행");
		int point = memberService.getMemberPoint(memberId);
		return point;
	}
	
	//포인트내역 조회
	@GetMapping("/list")
	public List<PointList> getPointList(@RequestBody String memberId){
		log.info("포인트내역 조회 실행");
		List<PointList> pointList = pointListService.getPointList(memberId);
		return pointList;
	}
	
	//포인트 적립(추가)
	@PostMapping("/save")
	public void insertSavePoint(@RequestBody SavePoint savePoint) {
		log.info("포인트 적립 실행");
		savePointService.insertPoint(savePoint);
	}
	
	//포인트 사용(추가)
	@PostMapping("/use")
	public void insertUsePoint(@RequestBody UsePoint usePoint) {
		log.info("포인트 사용 실행");
		usePointService.insertPoint(usePoint);
	}	
	
}

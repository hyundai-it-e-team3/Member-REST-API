package com.mycompany.memberAPI.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.SavePoint;
import com.mycompany.memberAPI.dto.UsePoint;
import com.mycompany.memberAPI.service.SavePointService;
import com.mycompany.memberAPI.service.UsePointService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/member/point")
public class PointController {
	
	@Resource
	private SavePointService savePointService;
	
	@Resource
	private UsePointService usePointService;
	
	//포인트내역 조회
	
	//포인트 적립(추가)
	@PostMapping("/save")
	public void insertSavePoint(@RequestBody SavePoint savePoint) {
		log.info("포인트 적립 실행");
		savePointService.insertPoint(savePoint);
	}
	
	//포인트 사용(수정)
	@PostMapping("/use")
	public void insertUsePoint(@RequestBody UsePoint usePoint) {
		log.info("포인트 사용 실행");
		usePointService.insertPoint(usePoint);
	}
	
	//포인트 환불
	
	
}

package com.mycompany.memberAPI.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.MemberDao;
import com.mycompany.memberAPI.dao.SavePointDao;
import com.mycompany.memberAPI.dto.Member;
import com.mycompany.memberAPI.dto.SavePoint;

@Service
public class SavePointService {
	
	@Resource
	private MemberDao memberDao;
	
	@Resource
	private SavePointDao savePointDao;
	
	public void insertPoint(SavePoint savePoint) {
		//회원 포인트 잔액 추가
		Member member = new Member();
		member.setMemberId(savePoint.getMemberId());
		member.setPoint(savePoint.getPoint());
		memberDao.updateSavePoint(member);
		
		//포인트 적립 내역 추가
		savePointDao.insertPoint(savePoint);
	}
	
}

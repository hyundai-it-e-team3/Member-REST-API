package com.mycompany.memberAPI.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.DetailPointDao;
import com.mycompany.memberAPI.dao.MemberDao;
import com.mycompany.memberAPI.dao.PointDao;
import com.mycompany.memberAPI.dto.DetailPoint;
import com.mycompany.memberAPI.dto.Member;
import com.mycompany.memberAPI.dto.Point;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PointService {
	
	@Resource
	private PointDao pointDao;
	
	@Resource
	private DetailPointDao detailPointDao;
	
	@Resource
	private MemberDao memberDao;
	
	public List<Point> getPointList(String memberId){
		return pointDao.getPointList(memberId);
	}
	
	public void insertSavePoint(Point savePoint) {
		log.info("회원 잔액 추가");
		Member member = new Member();
		member.setMemberId(savePoint.getMemberId());
		member.setPoint(savePoint.getPoint());
		memberDao.updatePointBalance(member);
		
		log.info("포인트 적립내역 추가 실행");
		pointDao.insertSavePoint(savePoint);
		
		log.info("상세포인트 적립내역 추가 실행");
		DetailPoint detailPoint = new DetailPoint();
		detailPoint.setMemberId(savePoint.getMemberId());
		detailPoint.setType(savePoint.getType());
		detailPoint.setPointSeq(savePoint.getPointSeq());
		detailPoint.setPoint(savePoint.getPoint());
		detailPoint.setBalance(savePoint.getPoint());
		
		detailPointDao.insertSaveDetailPoint(detailPoint);
	}
	
	public void insertUsePoint(Point usePoint) {
		int point = Math.abs(usePoint.getPoint()); //차감(음수) 양수로 변환
		
		log.info("회원 잔액 차감 실행");
		Member member = new Member();
		member.setMemberId(usePoint.getMemberId());
		member.setPoint(-point);
		memberDao.updatePointBalance(member);
		
		log.info("포인트 사용내역 추가 실행");
		pointDao.insertUsePoint(usePoint);
		
		log.info("상세포인트 사용내역 세팅-1");
		DetailPoint detailPoint = new DetailPoint();
		detailPoint.setMemberId(usePoint.getMemberId());
		detailPoint.setType(usePoint.getType());
		detailPoint.setPointSeq(usePoint.getPointSeq());

		log.info("<상세포인트 사용내역 추가 실행>");
		while(!(point == 0)) {
			log.info("가장 오래된 사용가능한 적립 포인트 내역 조회 및 실행");
			DetailPoint availableOlderPoint = detailPointDao.getAvailableOlderPoint(usePoint.getMemberId());
			detailPoint.setRefDetailPoinSeq(availableOlderPoint.getDetailPointSeq());
			
			int balance = availableOlderPoint.getBalance();
			
			if(balance >= point) {
				log.info("현재 사용가능한 적립포인트가 잔액이 충분한 경우");
				detailPoint.setPoint(-point);
				
				log.info("잔액 변경 실행");
				balance = balance - point;
				point = 0;
				detailPointDao.updateBalance(availableOlderPoint.getDetailPointSeq(), balance);
			} else {
				log.info("현재 사용가능한 적립포인트가 잔액이 부족한 경우");
				detailPoint.setPoint(-balance);
				
				point = point - balance;
				log.info("적립포인트 사용불가능 상태 및 잔액 0으로 변경 실행");
				detailPointDao.updateUsedStatus(availableOlderPoint.getDetailPointSeq());
			}
			
			log.info("상세포인트 사용내역 추가 실행");
			detailPointDao.insertUseDetailPoint(detailPoint);
		}
	}
}

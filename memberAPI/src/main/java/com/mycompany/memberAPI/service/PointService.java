package com.mycompany.memberAPI.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
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
	
	@Transactional
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
			detailPoint.setRefDetailPointSeq(availableOlderPoint.getDetailPointSeq());
			
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
	
	@Transactional
	public void insertRefundPoint(Point refundPoint) { // memberId, orderId
		log.info("환불 처리 과정 실행");
		
		log.info("Point 테이블--------------------");
		
		log.info("해당 주문과 관련된 포인트 내역 point_seq, point 데이터 불러오기");
		Point point = pointDao.selectRefundPoint(refundPoint); // return: pointSeq, point
		
		log.info("회원 잔액 되돌리기");
		Member member = new Member();
		member.setMemberId(refundPoint.getMemberId());
		member.setPoint(point.getPoint() * -1);
		memberDao.updatePointBalance(member);
		
		log.info("해당 주문과 관련된 포인트 내역 환불 처리 후 사용 포인트*(-1) 처리");
		refundPoint.setPoint(point.getPoint());
		pointDao.updateRefundPoint(refundPoint);
		
		log.info("Detail_Point 테이블-----------------");
		
		log.info("해당 포인트 내역과 관련된 상세 포인트 내역의 ref_detail_point_seq와 point 데이터 불러오기");
		List<DetailPoint> refundDetailPointList = detailPointDao.selectRefundDetailPoint(point);
		
		log.info("해당 상세 포인트 내역의 참조 사용한 포인트 내역의 status와 balance 되돌리기");
		for(DetailPoint detailPoint : refundDetailPointList) {
			detailPointDao.updateUsePointBalanceAndStatus(detailPoint);
		}
		
		log.info("마지막으로 해당 상세 포인트 내역의 환불 처리 후 포인트 0 처리");
		detailPointDao.updateRefundDetailPoint(point);
	}
}

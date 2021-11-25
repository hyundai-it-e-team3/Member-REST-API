package com.mycompany.memberAPI.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.DetailPointDao;
import com.mycompany.memberAPI.dao.MemberDao;
import com.mycompany.memberAPI.dao.SavePointDao;
import com.mycompany.memberAPI.dao.UsePointDao;
import com.mycompany.memberAPI.dto.DetailPoint;
import com.mycompany.memberAPI.dto.Member;
import com.mycompany.memberAPI.dto.SavePoint;
import com.mycompany.memberAPI.dto.UsePoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsePointService {
	
	@Resource
	private SavePointDao savePointDao;
	
	@Resource
	private UsePointDao usePointDao;
	
	@Resource
	private DetailPointDao detailPointDao;
	
	@Resource
	private MemberDao memberDao;
	
	public void insertPoint(UsePoint usePoint) {
		log.info("포인트 사용내역 추가 실행");
		usePointDao.insertPoint(usePoint);
		
		int point = usePoint.getPoint();
		
		log.info("회원의 잔액 차감 실행");
		Member member = new Member();
		member.setMemberId(usePoint.getMemberId());
		member.setPoint(point);
		//회원 포인트 잔액에서 사용 포인트 차감(수정)
		memberDao.updateUsePoint(member);
		
		//상세 사용 내역 저장:
		//1) 가장 오래된 적립 포인트를 조회&차감(수정)
		//2) 포인트가 부족할 경우 이후 오래된 적립 포인트를 가져와 차감하는 과정 반복(사용하려는 포인트를 만족할 때까지)
		//3) 반복되는 상세 사용 내역을 반복 저장
		while(!(point == 0)) {
			log.info("가장 오래된 사용가능한 적립 포인트 내역 조회 실행");
			SavePoint olderSavePoint = new SavePoint();
			olderSavePoint = savePointDao.getAvailableOlderPoint(usePoint.getMemberId());
			log.info(olderSavePoint.toString());
			
			log.info("포인트 상세내역 세팅");
			DetailPoint detailPoint = new DetailPoint();
			detailPoint.setSavePointSeq(olderSavePoint.getSavePointSeq());
			detailPoint.setUsePointSeq(usePoint.getUsePointSeq());
			
			int savePoint = olderSavePoint.getBalance();
			//savePoint가 현재 사용하려는 포인트보다 잔액이 많을 경우
			if(savePoint >= point) {
				log.info("현재 사용가능한 적립포인트가 잔액이 충분한 경우");
				detailPoint.setPoint(point);
				
				savePoint = savePoint - point;
				point = 0;
			//savePoint의 잔액이 부족할 경우
			} else {
				log.info("현재 사용가능한 적립포인트가 잔액이 부족한 경우");
				detailPoint.setPoint(savePoint);
				
				point = point - savePoint;
				savePoint = 0;
			}
			log.info("포인트 상세내역 추가 실행");
			detailPointDao.insertDetailPoint(detailPoint);
			
			//savePoint의 잔액이 0이 될 경우 사용불가능 상태로 변경
			if(savePoint == 0) {
				log.info("적립포인트 사용불가능으로 상태 변경 실행");
				olderSavePoint.setStatus('N');
			}

			log.info("사용한 적립포인트 내역 업데이트(잔액/사용상태) 실행");
			olderSavePoint.setBalance(savePoint);
			log.info(olderSavePoint.toString());
			savePointDao.updateBalance(olderSavePoint);
		}
	}

	
}

package com.mycompany.memberAPI.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.MemberDao;
import com.mycompany.memberAPI.dto.Member;
import com.mycompany.memberAPI.dto.MemberForOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	
	public enum JoinResult {
		SUCCESS,
		FAIL,
		DUPLICATE
	}
	
	@Resource
	private MemberDao memberDao;
	
	//회원가입
	public JoinResult joinMember(Member member) {
		log.info("가입 여부에 따른 회원가입 처리 과정 실행");
		
		//해당 아이디가 중복 아이디인지 확인
		Member dbMember = memberDao.getMember(member.getMemberId());
		
		//가입 여부에 따라서 회원가입 및 회원정보 DB 저장
		try {
			if(dbMember == null) {
				memberDao.insertMember(member);
				return JoinResult.SUCCESS;
			} else {
				return JoinResult.DUPLICATE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JoinResult.FAIL;
		}
	}
	
	//회원탈퇴
	public void deleteMember(String memberId) {
		log.info("실행");
		memberDao.deleteMember(memberId);
	}
	
	//로그인 시 마지막 로그인 날짜 업데이트
	public void updateLastLoginDate(String memberId) {
		log.info("실행");
		memberDao.updateLastLoginDate(memberId);
	}
	
	//회원정보 조회
	public Member getMember(String memberId) {
		return memberDao.getMember(memberId);
	}
	
	//회원정보 수정
	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}
	
	//주문을 위한 회원정보 조회
	public MemberForOrder getMemberForOrder(String memberId) {
		return memberDao.getMemberForOrder(memberId);
	}
	
	//회원 포인트 잔액 조회
	public int getMemberPoint(String memberId) {
		return memberDao.getMemberPoint(memberId);
	}
	
	//원클릭페이 결제비밀번호 설정
		public void updateAccountPassword(Member member) {
			memberDao.updateAccountPassword(member);
		}
	
}

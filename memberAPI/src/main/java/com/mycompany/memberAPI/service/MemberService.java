package com.mycompany.memberAPI.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.MemberDao;
import com.mycompany.memberAPI.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	
	public enum JoinResult {
		SUCCESS,
		FAIL,
		DUPLICATED
	}
	
	@Resource
	private MemberDao memberDao;
	
	//회원가입
	public JoinResult joinMember(Member member) {
		log.info("가입 여부에 따른 회원가입 처리 과정 실행");
		
		//해당 아이디가 중복 아이디인지 확인
		Member dbMember = memberDao.selectByMemberId(member.getMemberId());
		
		//가입 여부에 따라서 회원가입 및 회원정보 DB 저장
		try {
			if(dbMember == null) {
				memberDao.insertMember(member);
				return JoinResult.SUCCESS;
			} else {
				return JoinResult.DUPLICATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JoinResult.FAIL;
		}
	}
	
	public void deleteMember(String memberId) {
		log.info("실행");
		memberDao.deleteMember(memberId);
	}
	
}

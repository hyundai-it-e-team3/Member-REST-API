package com.mycompany.memberAPI.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.Member;
import com.mycompany.memberAPI.dto.MemberForOrder;

@Mapper
public interface MemberDao {
	public Member getMember(String memberId);
	public void insertMember(Member member);
	public void deleteMember(String memberId);
	public void updateLastLoginDate(String memberId);
	public void updateMember(Member member);
	public MemberForOrder getMemberForOrder(String memberId);
	public void updateSavePoint(Member member);
	public void updateUsePoint(Member member);
	public int getMemberPoint(String memberId);
}

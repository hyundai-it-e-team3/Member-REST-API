package com.mycompany.memberAPI.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.Member;

@Mapper
public interface MemberDao {
	public Member selectByMemberId(String memberId);
	public void insertMember(Member member);
	public void deleteMember(String memberId);
}

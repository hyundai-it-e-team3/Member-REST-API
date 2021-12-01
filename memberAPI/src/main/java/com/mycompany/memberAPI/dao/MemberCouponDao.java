package com.mycompany.memberAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.MemberCoupon;

@Mapper
public interface MemberCouponDao {
	public List<MemberCoupon> getMemberCoupon(String memberId);
	public void insertMemberCoupon(MemberCoupon memberCoupon);
	public void updateMemberCoupon(MemberCoupon memberCoupon);
	public MemberCoupon getDuplicateCoupon(MemberCoupon memberCoupon);
}

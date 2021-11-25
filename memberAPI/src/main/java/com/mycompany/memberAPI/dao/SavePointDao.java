package com.mycompany.memberAPI.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.SavePoint;

@Mapper
public interface SavePointDao {
	public void insertPoint(SavePoint savePoint);
	public SavePoint getAvailableOlderPoint(String memberId);
	public void updateBalance(SavePoint savePoint);
}

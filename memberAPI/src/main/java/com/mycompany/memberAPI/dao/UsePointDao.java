package com.mycompany.memberAPI.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.UsePoint;

@Mapper
public interface UsePointDao {
	public void insertPoint(UsePoint usePoint);
}

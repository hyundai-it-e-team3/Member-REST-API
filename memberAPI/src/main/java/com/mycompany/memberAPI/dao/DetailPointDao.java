package com.mycompany.memberAPI.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.DetailPoint;

@Mapper
public interface DetailPointDao {
	public void insertDetailPoint(DetailPoint detailPoint);
}

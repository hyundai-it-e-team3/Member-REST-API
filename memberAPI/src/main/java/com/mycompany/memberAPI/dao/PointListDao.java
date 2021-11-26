package com.mycompany.memberAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.PointList;

@Mapper
public interface PointListDao {
	public List<PointList> getPointList(String memberId);
}

package com.mycompany.memberAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.Point;

@Mapper
public interface PointDao {
	public List<Point> getPointList(String memberId);
	public void insertSavePoint(Point savePoint);
	public void insertUsePoint(Point usePoint);
	public void updateRefundPoint(Point refundPoint);
	public List<String> selectRefundPoint(Point refundPoint);
}

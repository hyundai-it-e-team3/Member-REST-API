package com.mycompany.memberAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.memberAPI.dto.DetailPoint;
import com.mycompany.memberAPI.dto.Point;

@Mapper
public interface DetailPointDao {
	public void insertSaveDetailPoint(DetailPoint saveDetailPoint);
	public DetailPoint getAvailableOlderPoint(String memberId);
	public void insertUseDetailPoint(DetailPoint useDetailPoint);
	public void updateBalance(@Param("detailPointSeq") int detailPointSeq, @Param("balance") int balance);
	public void updateUsedStatus(int detailPointSeq);
	public List<DetailPoint> selectRefundDetailPoint(Point refundPointSeq);
	public void updateUsePointBalanceAndStatus(DetailPoint detailPoint);
	public void updateRefundDetailPoint(Point refundPointSeq);
}

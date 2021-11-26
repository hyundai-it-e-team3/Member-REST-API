package com.mycompany.memberAPI.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.PointListDao;
import com.mycompany.memberAPI.dto.PointList;

@Service
public class PointListService {
	
	@Resource
	private PointListDao pointListDao;
	
	public List<PointList> getPointList(String memberId) {
		return pointListDao.getPointList(memberId);
	}
}

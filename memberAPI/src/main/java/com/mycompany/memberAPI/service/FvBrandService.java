package com.mycompany.memberAPI.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.FvBrandDao;
import com.mycompany.memberAPI.dto.FvBrand;

@Service
public class FvBrandService {
	@Resource
	private FvBrandDao fvBrandDao;
	
	public List<FvBrand> getFvBrand(String memberId) {
		return fvBrandDao.getFvBrand(memberId);
	}
	
	public void insertBrand(FvBrand fvBrand) {
		fvBrandDao.insertBrand(fvBrand);
	}
	
	public void deleteBrand(FvBrand fvBrand) {
		fvBrandDao.deleteBrand(fvBrand);
	}
}

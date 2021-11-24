package com.mycompany.memberAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.FvBrand;
import com.mycompany.memberAPI.dto.Wishlist;

@Mapper
public interface FvBrandDao {
	public List<FvBrand> getFvBrand(String memberId);
	public void insertBrand(FvBrand fvBrand);
	public void deleteBrand(FvBrand fvBrand);
}

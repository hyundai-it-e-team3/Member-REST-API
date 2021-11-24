package com.mycompany.memberAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.Wishlist;

@Mapper
public interface WishlistDao {
	public List<Wishlist> getWishlist(String memberId);
	public void insertProduct(Wishlist wishlist);
	public void deleteProduct(Wishlist wishlist);
}

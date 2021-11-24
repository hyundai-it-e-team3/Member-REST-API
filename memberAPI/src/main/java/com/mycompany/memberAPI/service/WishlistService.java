package com.mycompany.memberAPI.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.WishlistDao;
import com.mycompany.memberAPI.dto.Wishlist;

@Service
public class WishlistService {
	@Resource
	private WishlistDao wishlistDao;
	
	public List<Wishlist> getWishlist(String memberId) {
		return wishlistDao.getWishlist(memberId);
	}
	
	public void insertProduct(Wishlist wishlist) {
		wishlistDao.insertProduct(wishlist);
	}
	
	public void deleteProduct(Wishlist wishlist) {
		wishlistDao.deleteProduct(wishlist);
	}
}

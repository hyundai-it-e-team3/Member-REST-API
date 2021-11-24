package com.mycompany.memberAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.Wishlist;
import com.mycompany.memberAPI.service.WishlistService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Resource
	private WishlistService wishlistService;
	
	@GetMapping
	public List<Wishlist> getWishlist(@RequestBody String memberId) {
		log.info("WISHLIST 조회");
		List<Wishlist> wishlist = wishlistService.getWishlist(memberId);
		return wishlist;
	}
	
	@PostMapping
	public void insertProduct(@RequestBody Wishlist wishlist) {
		log.info("WISHLIST 추가");
		wishlistService.insertProduct(wishlist);
	}
	
	@DeleteMapping
	public void deleteProduct(@RequestBody Wishlist wishlist) {
		log.info("WISHLIST 삭제");
		wishlistService.deleteProduct(wishlist);
	}
}

package com.mycompany.memberAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.FvBrand;
import com.mycompany.memberAPI.service.FvBrandService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/fvbrand")
public class FvBrandController {
	
	@Resource
	private FvBrandService fvBrandService;
	
	@GetMapping
	public List<FvBrand> getWishlist(@RequestBody String memberId) {
		log.info("선호브랜드 조회");
		List<FvBrand> fvBrand = fvBrandService.getFvBrand(memberId);
		return fvBrand;
	}
	
	@PostMapping
	public void insertBrand(@RequestBody FvBrand fvBrand) {
		log.info("선호브랜드 추가");
		fvBrandService.insertBrand(fvBrand);
	}
	
	@DeleteMapping
	public void deleteBrand(@RequestBody FvBrand fvBrand) {
		log.info("선호브랜드 삭제");
		fvBrandService.deleteBrand(fvBrand);
	}
}

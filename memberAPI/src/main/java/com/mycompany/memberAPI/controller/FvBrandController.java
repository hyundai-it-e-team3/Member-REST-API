package com.mycompany.memberAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{memberId}")
	public List<FvBrand> getWishlist(@PathVariable String memberId) {
		log.info("선호브랜드 조회");
		return fvBrandService.getFvBrand(memberId);
	}
	
	@PostMapping
	public void insertBrand(@RequestBody FvBrand fvBrand) {
		log.info("선호브랜드 추가");
		fvBrandService.insertBrand(fvBrand);
	}
	
	@DeleteMapping("/{memberId}/{brandName}")
	public void deleteBrand(@PathVariable String memberId,  @PathVariable String brandName) {
		log.info("선호브랜드 삭제");
		FvBrand fvBrand = new FvBrand();
		fvBrand.setBrandName(brandName);
		fvBrand.setMemberId(memberId);
		fvBrandService.deleteBrand(fvBrand);
	}
}

package com.mycompany.memberAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.Address;
import com.mycompany.memberAPI.service.AddressService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Resource
	private AddressService addressService;
	
	//기본배송지 조회
	@GetMapping("/{memberId}")
	public Address getDefaultAddress(@PathVariable String memberId) {
		return addressService.getDefaultAddress(memberId);
	}
	
	//주소 목록 조회
	@GetMapping("/list/{memberId}")
	public List<Address> getAddressList(@PathVariable String memberId) {
		log.info("주소 목록 조회");
		List<Address> address = addressService.getAddressList(memberId);
		return address;
	}
	
	//주소 조회
	@GetMapping("/{addressSeq}")
	public Address getAddress(@PathVariable String addressSeq) {
		log.info("주소 조회");
		Address address = addressService.getAddress(addressSeq);
		return address;
	}
	
	//주소 추가
	@PostMapping
	public void insertAddress(@RequestBody Address address) {
		log.info("주소 추가");
		addressService.insertAddress(address);
	}
	
	//주소 수정
	@PatchMapping
	public void updateAddress(@RequestBody Address address) {
		log.info("주소 수정");
		addressService.updateAddress(address);
	}
	
	//주소 삭제
	@DeleteMapping("/{addressSeq}")
	public void deleteAddress(@PathVariable String addressSeq) {
		log.info("주소 삭제");
		log.info(addressSeq.toString());
		addressService.deleteAddress(addressSeq);
	}
	
}

package com.mycompany.memberAPI.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.AddressDao;
import com.mycompany.memberAPI.dto.Address;

@Service
public class AddressService {
	
	@Resource
	private AddressDao addressDao;
	
	public List<Address> getAddress(String memberId) {
		List<Address> address = addressDao.getAddress(memberId);
		return address;
	}
	
	public void insertAddress(Address address) {
		addressDao.insertAddress(address);
	}
	
	public void updateAddress(Address address) {
		addressDao.updateAddress(address);
	}
	
	public void deleteAddress(Address address) {
		addressDao.deleteAddress(address);
	}
	
}

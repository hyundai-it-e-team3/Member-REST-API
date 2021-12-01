package com.mycompany.memberAPI.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.memberAPI.dao.AddressDao;
import com.mycompany.memberAPI.dto.Address;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressService {
	
	@Resource
	private AddressDao addressDao;
	
	public Address getDefaultAddress(String memberId) {
		return addressDao.getDefaultAddress(memberId);
	}
	
	public List<Address> getAddressList(String memberId) {
		List<Address> address = addressDao.getAddressList(memberId);
		return address;
	}
	
	public Address getAddress(String addressSeq) {
		return addressDao.getAddress(addressSeq);
	}
	
	public void insertAddress(Address address) {
		String defaultAddress = address.getDefaultAddress();
		if(defaultAddress.equals("1")) {
			addressDao.updateDefaultAddressAddress(address.getMemberId());
			addressDao.insertAddress(address);
		} else {
			addressDao.insertAddress(address);
		}
		
	}
	
	public void updateAddress(Address address) {
		String defaultAddress = address.getDefaultAddress();
		log.info(defaultAddress);
		if(defaultAddress.equals("1")) {
			addressDao.updateDefaultAddressAddress(address.getMemberId());
			addressDao.updateAddress(address);
		} else {
			addressDao.updateAddress(address);
		}
	}
	
	public void deleteAddress(String addressSeq) {
		addressDao.deleteAddress(addressSeq);
	}
	
}

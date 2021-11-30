package com.mycompany.memberAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.Address;

@Mapper
public interface AddressDao {
	public List<Address> getAddressList(String memberId);
	public Address getAddress(String addressSeq);
	public void insertAddress(Address address);
	public void updateAddress(Address address);
	public void deleteAddress(String addressSeq);
	public void updateDefaultAddressAddress(String memberId);
}

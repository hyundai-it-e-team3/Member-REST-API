package com.mycompany.memberAPI.dto;

import lombok.Data;

@Data
public class Address {
	private int addressSeq;
	private String name;
	private int zipCode;
	private String address1;
	private String address2;
	private String tel;
	private String memberId;
	private char defaultAddress;
}

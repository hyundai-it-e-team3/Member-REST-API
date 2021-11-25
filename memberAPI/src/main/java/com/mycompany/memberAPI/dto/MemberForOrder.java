package com.mycompany.memberAPI.dto;

import lombok.Data;

@Data
public class MemberForOrder {
	private String orderName;
	private String orderTel;
	private String deliveryName;
	private String deliveryTel;
	private int zipCode;
	private String address1;
	private String address2;
}

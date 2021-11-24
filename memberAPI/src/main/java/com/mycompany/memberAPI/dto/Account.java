package com.mycompany.memberAPI.dto;

import lombok.Data;

@Data
public class Account {
	private String accountNo;
	private String bank;
	private char payType;
	private String memberId;
}

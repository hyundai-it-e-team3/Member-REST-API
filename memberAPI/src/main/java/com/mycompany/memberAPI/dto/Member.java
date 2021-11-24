package com.mycompany.memberAPI.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String memberId;
	private String password;
	private String name;
	private String nickname;
	private String email;
	private String tel;
	private Date birthday;
	private char memberLevel;
	private String oneclickpayPassword;
	private String kakaoNo;
	private String memberRole;
	private char status;
	private Date lastLoginDate;
}

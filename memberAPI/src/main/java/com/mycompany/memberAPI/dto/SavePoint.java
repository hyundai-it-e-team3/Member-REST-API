package com.mycompany.memberAPI.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SavePoint {
	private int savePointSeq;
	private int point;
	private int balance;
	private String content;
	private Date regDate;
	private Date expDate;
	private char status;
	private String memberId;
}

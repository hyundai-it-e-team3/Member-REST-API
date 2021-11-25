package com.mycompany.memberAPI.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UsePoint {
	private String memberId; //DB에는 X
	
	private int usePointSeq;
	private int point;
	private String content;
	private Date regDate;
}

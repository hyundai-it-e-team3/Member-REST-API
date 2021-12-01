package com.mycompany.memberAPI.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DetailPoint {
	private int detailPointSeq;
	private String memberId;
	private String type;
	private int pointSeq;
	private char status;
	private Date regDate;
	private int point;
	private int balance;
	private int refDetailPoinSeq;
}

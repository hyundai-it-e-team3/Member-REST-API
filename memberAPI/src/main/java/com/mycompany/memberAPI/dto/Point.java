package com.mycompany.memberAPI.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Point {
	private int pointSeq;
	private String memberId;
	private String orderId;
	private String type;
	private int point;
	private Date regDate;
}

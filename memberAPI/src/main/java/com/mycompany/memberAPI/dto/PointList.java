package com.mycompany.memberAPI.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PointList {
	private int savePointSeq;
	private int savePoint;
	private char saveType;
	private Date saveRegDate;
	private Date saveExpDate;
	private int usePointSeq;
	private int usePoint;
	private char useType;
	private Date useRegDate;
}

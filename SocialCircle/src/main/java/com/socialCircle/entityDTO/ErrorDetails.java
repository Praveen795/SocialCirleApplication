package com.socialCircle.entityDTO;

import java.util.Date;


public class ErrorDetails {
	private Date timeStamp;
	private String massage;
	private String detail;
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public String getMassage() {
		return massage;
	}
	public String getDetail() {
		return detail;
	}
	public ErrorDetails(java.util.Date date, String massage, String detail) {
		super();
		this.timeStamp = date;
		this.massage = massage;
		this.detail = detail;
	}
	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}

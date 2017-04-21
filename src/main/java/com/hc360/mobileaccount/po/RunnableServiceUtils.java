package com.hc360.mobileaccount.po;


public class RunnableServiceUtils implements Runnable{
	
	private String phone;
	
	private String mark;
	
	public RunnableServiceUtils(String phone, String mark){
		this.setPhone(phone);
		this.setMark(mark);
	}
	
	public void run() {
		
		
		
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

}

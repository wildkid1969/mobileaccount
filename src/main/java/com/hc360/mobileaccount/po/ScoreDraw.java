package com.hc360.mobileaccount.po;

import java.util.Date;

public class ScoreDraw {
	
	private int id;
	
	private String phone;
	
	private String score;
	
	private String result;
	
	private Date actiontime = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getActiontime() {
		return actiontime;
	}

	public void setActiontime(Date actiontime) {
		this.actiontime = actiontime;
	}

}

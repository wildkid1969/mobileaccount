package com.hc360.mobileaccount.po;

public class Score {
	
	private String phone;
	
	private String type;
	
	private String scorenum;
	
	private String scorefrom;
	
	private String marktime;
	
	private String updatetime;
	
	private String name;
	
	private String appType; // 114 168 
	
	private int action;// 1 增加积分 2 消耗积分 
	
	public String getScorefrom() {
		return scorefrom;
	}

	public void setScorefrom(String scorefrom) {
		this.scorefrom = scorefrom;
	}

	public String getMarktime() {
		return marktime;
	}

	public void setMarktime(String marktime) {
		this.marktime = marktime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScorenum() {
		return scorenum;
	}

	public void setScorenum(String scorenum) {
		this.scorenum = scorenum;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

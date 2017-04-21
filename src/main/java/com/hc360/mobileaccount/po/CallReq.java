package com.hc360.mobileaccount.po;

import java.util.Date;

public class CallReq extends YzxCall{
	
	private int callerchargetype; // 主叫号码计费类型 0 开发者计费 1 平台计费
	
	private float callerbalance; // 在平台主叫账户钱包余额
	
	private String userData; // 用户自定义字段
	
	private Date calltime;

	public int getCallerchargetype() {
		return callerchargetype;
	}

	public void setCallerchargetype(int callerchargetype) {
		this.callerchargetype = callerchargetype;
	}

	public float getCallerbalance() {
		return callerbalance;
	}

	public void setCallerbalance(float callerbalance) {
		this.callerbalance = callerbalance;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public Date getCalltime() {
		return calltime;
	}

	public void setCalltime(Date calltime) {
		this.calltime = calltime;
	}

}

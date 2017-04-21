package com.hc360.mobileaccount.po;

public class CallStatExcelLog {
	
	private String caller; // 主叫号码
	
	private String called; // 被叫号码
	
	private String calltime; // 通话时间
	
	private int during; // 通话时长
	
	private String sw; // 搜索词 local 代表本地电话 否则为黄页电话

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getCalled() {
		return called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public String getCalltime() {
		return calltime;
	}

	public void setCalltime(String calltime) {
		this.calltime = calltime;
	}

	public int getDuring() {
		return during;
	}

	public void setDuring(int during) {
		this.during = during;
	}

	public String getSw() {
		return sw;
	}

	public void setSw(String sw) {
		this.sw = sw;
	}

}

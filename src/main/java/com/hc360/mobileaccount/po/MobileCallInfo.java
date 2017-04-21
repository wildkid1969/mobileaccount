package com.hc360.mobileaccount.po;

import java.util.Date;

public class MobileCallInfo {
	
	private int id; // 自增序列
	
	private String phone; // 主播电话
	
	private String callid; // 主叫号码
	
	private String calledid; // 被叫号码
	
	private Date starttime; // 拨号开始时间
	
	private Date endtime; // 拨号结束时间
	
	private String type; // 拨号类型  云之讯 yzx 国领 gl
	
	private String appyype; // app类型 114 168
	
	private long length; // 拨号时长 秒数 s

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

	public String getCallid() {
		return callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

	public String getCalledid() {
		return calledid;
	}

	public void setCalledid(String calledid) {
		this.calledid = calledid;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getLength() {
		return this.length;
	}

	public void setLength(long length ) {		
		this.length = length;
	}

	public String getAppyype() {
		return appyype;
	}

	public void setAppyype(String appyype) {
		this.appyype = appyype;
	}	

}

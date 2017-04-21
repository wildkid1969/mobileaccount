package com.hc360.mobileaccount.po;

import java.util.Date;

public class YzxCall {
	
	private String event; // 回调类型
	
	private String callid; //呼叫的唯一标示 由sdk声称
	
	private String accountid; // 开发者账号ID
	
	private String appid; //应用ID
	
	private int calltype; // 0 直播 1 免费 2 回拨
	
	private int callertype; // 呼叫号码类型 0 client账号 1 普通电话
			
	private String caller; //主叫号码
	
	private int calledtype; // 被叫号码类型 0 client账号 1 普通电话
	
	private String called; // 被叫号码
	
	private String confid; // 群聊ID
	
	private String starttime; //开始通话时间  时间格式如：2014-06-16 16:47:28
	
	private String stoptime; // 结束通话时间  时间格式如：2014-06-16 16:47:28
	
	private int length ; // 通话时长 秒数
	
	private String recordurl; // 通话录音完整下载地址
	
	private String userData; // 用户自定义数据字符串
	
	private int reason; // 挂机原因 0：正常挂断；1：余额不足；2：媒体超时； 
						// 3：无法接通；4：拒接；5：超时未接；6：拒接或超时未接； 
						// 7：平台服务器网络错误；8：用户请求取消通话；9：第三方鉴权错误；255：其他原因。
	private int subreason; //挂机原因补充描述 1 主叫挂断 2 被叫挂断； 目前当reason=0时有效
	
	private int callerchargetype; // 主叫号码计费类型 0 开发者计费 1 平台计费

	private float callerbalance; // 在平台主叫账户钱包余额
		
	public String getConfid() {
		return confid;
	}

	public void setConfid(String confid) {
		this.confid = confid;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRecordurl() {
		return recordurl;
	}

	public void setRecordurl(String recordurl) {
		this.recordurl = recordurl;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public int getSubreason() {
		return subreason;
	}

	public void setSubreason(int subreason) {
		this.subreason = subreason;
	}

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

	public Date getCalltime() {
		return calltime;
	}

	public void setCalltime(Date calltime) {
		this.calltime = calltime;
	}

	private Date calltime;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getCallid() {
		return callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public int getCalltype() {
		return calltype;
	}

	public void setCalltype(int calltype) {
		this.calltype = calltype;
	}

	public int getCallertype() {
		return callertype;
	}

	public void setCallertype(int callertype) {
		this.callertype = callertype;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public int getCalledtype() {
		return calledtype;
	}

	public void setCalledtype(int calledtype) {
		this.calledtype = calledtype;
	}

	public String getCalled() {
		return called;
	}

	public void setCalled(String called) {
		this.called = called;
	}
		
}

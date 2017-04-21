package com.hc360.mobileaccount.po;

import java.util.Date;

public class CallLogStat {

	private String p1; // 本机号码

	private String p1name; // 本机公司名称

	private String p1corpid; // 本机公司ID

	private String p2; // 对方号码

	private String p2name; // 对方公司名称

	private String p2corpid; // 对方公司ID

	private String kind; // 1 主叫 2被叫

	private String apptype; // app 类型 114 或者 168

	private Date calltime = new Date(); // 通话时间

	private String sw; // 搜索词

	private String during; // 通话时长

	private String dtype; // 机型

	private String dos; // 操作系统

	private String msgid; // 来源消息ID

	private String corpid; // 公司ID
	
	private String corpName; // 公司名称

	private String deviceid;// 设备唯一编号

	public String getSw() {
		return sw;
	}

	public void setSw(String sw) {
		this.sw = sw;
	}

	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getP1name() {
		return p1name;
	}

	public void setP1name(String p1name) {
		this.p1name = p1name;
	}

	public String getP2name() {
		return p2name;
	}

	public void setP2name(String p2name) {
		this.p2name = p2name;
	}

	public String getP1corpid() {
		return p1corpid;
	}

	public void setP1corpid(String p1corpid) {
		this.p1corpid = p1corpid;
	}

	public String getP2corpid() {
		return p2corpid;
	}

	public void setP2corpid(String p2corpid) {
		this.p2corpid = p2corpid;
	}

	public Date getCalltime() {
		return calltime;
	}

	public void setCalltime(Date calltime) {
		this.calltime = calltime;
	}

	public String getDuring() {
		return during;
	}

	public void setDuring(String during) {
		this.during = during;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getDos() {
		return dos;
	}

	public void setDos(String dos) {
		this.dos = dos;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

}

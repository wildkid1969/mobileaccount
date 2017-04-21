package com.hc360.mobileaccount.po;

public class CorpInfoExt {
	/*
	 * ID标识
	 */
	private long id = 0;

	/*
	 * 企业数据CORPID标识
	 */
	private long corpid = 0;

	/*
	 * 企业浏览次数
	 */
	private long accessnum = 0;

	/*
	 * 拨打次数
	 */
	private long callnum = 0;

	/*
	 * 接通次数
	 */
	private long callednum = 0;

	/*
	 * 接通率
	 */
	private String calledrate = "0.00";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCorpid() {
		return corpid;
	}

	public void setCorpid(long corpid) {
		this.corpid = corpid;
	}

	public long getAccessnum() {
		return accessnum;
	}

	public void setAccessnum(long accessnum) {
		this.accessnum = accessnum;
	}

	public long getCallnum() {
		return callnum;
	}

	public void setCallnum(long callnum) {
		this.callnum = callnum;
	}

	public long getCallednum() {
		return callednum;
	}

	public void setCallednum(long callednum) {
		this.callednum = callednum;
	}

	public String getCalledrate() {
		return calledrate;
	}

	public void setCalledrate(String calledrate) {
		this.calledrate = calledrate;
	}
}
package com.hc360.mobileaccount.po;

import java.util.Date;

public class BalanceLog {

	private int id;

	private String phone;

	private String flownumber; // 流水号

	private int yzxbalance; // 云之讯划拨秒数

	private int length; // 时长 单位秒 (s)

	private Date ctime = new Date(); // 操作时间

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

	public String getFlownumber() {
		return flownumber;
	}

	public void setFlownumber(String flownumber) {
		this.flownumber = flownumber;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public int getYzxbalance() {
		return yzxbalance;
	}

	public void setYzxbalance(int yzxbalance) {
		this.yzxbalance = yzxbalance;
	}

}

package com.hc360.mobileaccount.po;

import java.util.List;

public class FansGroup {
	private String subtime;
	private List<Fans> fans;

	public String getSubtime() {
		return subtime;
	}
	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}
	public List<Fans> getFans() {
		return fans;
	}
	public void setFans(List<Fans> fans) {
		this.fans = fans;
	}
}
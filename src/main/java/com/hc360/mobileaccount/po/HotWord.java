package com.hc360.mobileaccount.po;

public class HotWord {
	private int id;

	private char type;

	private String k1;

	private String k2;

	private String k3;

	private String k4;

	private boolean enable;

	private String updatetime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getK1() {
		return k1;
	}

	public void setK1(String k1) {
		this.k1 = k1;
	}

	public String getK2() {
		return k2;
	}

	public void setK2(String k2) {
		this.k2 = k2;
	}

	public String getK3() {
		return k3;
	}

	public void setK3(String k3) {
		this.k3 = k3;
	}

	public String getK4() {
		return k4;
	}

	public void setK4(String k4) {
		this.k4 = k4;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
}

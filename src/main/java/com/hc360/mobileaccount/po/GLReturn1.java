package com.hc360.mobileaccount.po;

import java.util.List;

public class GLReturn1 {
	
	private int code;
	
	private String msg;
	
	private List<GlData> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GlData> getData() {
		return data;
	}

	public void setData(List<GlData> data) {
		this.data = data;
	}
}

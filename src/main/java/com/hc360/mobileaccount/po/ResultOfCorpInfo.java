package com.hc360.mobileaccount.po;

import java.util.List;

public class ResultOfCorpInfo {

	private int pagesize;
	
	private int pageNum;
	
	private int allNum;
	
	private List<CorpInfo> corpInfoList;

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<CorpInfo> getCorpInfoList() {
		return corpInfoList;
	}

	public void setCorpInfoList(List<CorpInfo> corpInfoList) {
		this.corpInfoList = corpInfoList;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}
	
	
}

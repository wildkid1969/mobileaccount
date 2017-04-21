package com.hc360.mobileaccount.po;

import java.util.ArrayList;
import java.util.List;

/*
 * 企业搜索结果
 */
public class SearchCorpInfoList {
	/*
	 * 错误消息
	 */
	String error = "";

	/*
	 * 使用MD5加密的搜索关键词
	 */
	String searchKeyWordMd5 = "";

	/*
	 * 结果的总条数
	 */
	String searchResultfoAllNum = "0";

	/*
	 * 每页多少条
	 */
	String searchResultfoNum = "0";

	/*
	 * 搜索结果LIST
	 */
	List<SearchCorpInfo> searchResultInfo = new ArrayList<SearchCorpInfo>();

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getSearchKeyWordMd5() {
		return searchKeyWordMd5;
	}
	public void setSearchKeyWordMd5(String searchKeyWordMd5) {
		this.searchKeyWordMd5 = searchKeyWordMd5;
	}
	public String getSearchResultfoAllNum() {
		return searchResultfoAllNum;
	}
	public void setSearchResultfoAllNum(String searchResultfoAllNum) {
		this.searchResultfoAllNum = searchResultfoAllNum;
	}
	public String getSearchResultfoNum() {
		return searchResultfoNum;
	}
	public void setSearchResultfoNum(String searchResultfoNum) {
		this.searchResultfoNum = searchResultfoNum;
	}
	public List<SearchCorpInfo> getSearchResultInfo() {
		return searchResultInfo;
	}
	public void setSearchResultInfo(List<SearchCorpInfo> searchResultInfo) {
		this.searchResultInfo = searchResultInfo;
	}
}
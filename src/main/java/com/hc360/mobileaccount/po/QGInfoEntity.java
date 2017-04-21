package com.hc360.mobileaccount.po;

import java.util.List;

public class QGInfoEntity {

	private String searchResultfoParameterName;

	private String error;

	private String searchKeyWordMd5;

	private String searchResultfoAllNum;

	private String searchResultfoNum;

	private String userPhone;

	private String address;

	private String contact;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	private String telphone;

	private List<searchResultInfo> searchResultInfo;

	public void setSearchResultfoParameterName(String searchResultfoParameterName) {
		this.searchResultfoParameterName = searchResultfoParameterName;
	}

	public String getSearchResultfoParameterName() {
		return this.searchResultfoParameterName;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return this.error;
	}

	public void setSearchKeyWordMd5(String searchKeyWordMd5) {
		this.searchKeyWordMd5 = searchKeyWordMd5;
	}

	public String getSearchKeyWordMd5() {
		return this.searchKeyWordMd5;
	}

	public void setSearchResultfoAllNum(String searchResultfoAllNum) {
		this.searchResultfoAllNum = searchResultfoAllNum;
	}

	public String getSearchResultfoAllNum() {
		return this.searchResultfoAllNum;
	}

	public void setSearchResultfoNum(String searchResultfoNum) {
		this.searchResultfoNum = searchResultfoNum;
	}

	public String getSearchResultfoNum() {
		return this.searchResultfoNum;
	}

	public void setSearchResultInfo(List<searchResultInfo> searchResultInfo) {
		this.searchResultInfo = searchResultInfo;
	}

	public List<searchResultInfo> getSearchResultInfo() {
		return this.searchResultInfo;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}
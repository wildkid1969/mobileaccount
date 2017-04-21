package com.hc360.mobileaccount.po;

public class ConfigUtil {
	
	private int companyValue; // 匹配多少个算是企业用户，其他为个人用户
	
	private int uploadTime; // 上传电话号时间间隔

	public int getCompanyValue() {
		return companyValue;
	}

	public void setCompanyValue(int companyValue) {
		this.companyValue = companyValue;
	}

	public int getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(int uploadTime) {
		this.uploadTime = uploadTime;
	}

}

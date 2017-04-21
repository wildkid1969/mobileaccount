package com.hc360.mobileaccount.po;

import java.util.List;

public class CompanyInfo {
	
	private String companyName;
	
	private String mmtlevel;
	
	private String introduce;
	
	private String userid;
	
	private List<ProductInfo> products;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<ProductInfo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductInfo> products) {
		this.products = products;
	}

	public String getMmtlevel() {
		return mmtlevel;
	}

	public void setMmtlevel(String mmtlevel) {
		this.mmtlevel = mmtlevel;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	

}

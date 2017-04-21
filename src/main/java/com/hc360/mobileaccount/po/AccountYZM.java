package com.hc360.mobileaccount.po;

public class AccountYZM {
	
	private String accountid;
	
	private String shortsecret;
	
	private String pwd;
	
	private String apptype; // app type 168 、114
	
	private String type;//验证码类型
	
	private String createtime;//密钥开始时间
	
	private String during;//密钥有效期
	
	private String phone; // 接收短信的电话号码
	
	public boolean checkPara(){
		if(this.phone != null && this.type != null){
			return true;
		}else{
			return false;
		}
	}
	
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getShortsecret() {
		return shortsecret;
	}

	public void setShortsecret(String shortsecret) {
		this.shortsecret = shortsecret;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDuring() {
		return during;
	}

	public void setDuring(String during) {
		this.during = during;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String appType) {
		this.apptype = appType;
	}


}

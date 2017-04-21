package com.hc360.mobileaccount.po;

public class AccountYzx {
	
	private String accountId;
	
	private String phone;  // 电话号码
	
	private String appid; // 应用Id
	
	private String clientNumber; // 云之讯返回的账号
	
	private String clientPwd; // 云之讯返回的密码
	
	private String friendlyName;// Client名称
	
	private String createDate; // 创建日期
	
	private String clientType; // 0 开发者计费；1 云平台计费。默认为0。
	
	private String clienttype;
	
	private String charge;
	
	private String balance; // 余额时长
	
	private String apptype; // 168 或 114
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getClientPwd() {
		return clientPwd;
	}

	public void setClientPwd(String clientPwd) {
		this.clientPwd = clientPwd;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getClienttype() {
		return clienttype;
	}

	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}	

}

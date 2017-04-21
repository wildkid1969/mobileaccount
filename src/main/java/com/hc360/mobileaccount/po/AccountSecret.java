package com.hc360.mobileaccount.po;

public class AccountSecret {

	/*
	 * @用户数字ID
	 */
	private String phone;

	/*
	 * @密码 MD5
	 */
	private String secret;

	/*
	 * @创建时间
	 */
	private String createtime;
	/*
	 * @更新时间
	 */
	private String updatetime;
	/*
	 * @最后一次登录时间
	 */
	private String logintime;
	
	private String apptype ; // app 类型 114 或者 168
	
	private String accountid;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String l) {
		this.createtime = l;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String type) {
		this.apptype = type;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

}

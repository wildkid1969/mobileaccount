package com.hc360.mobileaccount.po;

public class YZXReturnMsg {

	private String respCode;
	
	private AccountYzx client;
	
	private YzxAccount account;

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public AccountYzx getClient() {
		return client;
	}

	public void setClient(AccountYzx client) {
		this.client = client;
	}

	public YzxAccount getAccount() {
		return account;
	}

	public void setAccount(YzxAccount account) {
		this.account = account;
	}
}

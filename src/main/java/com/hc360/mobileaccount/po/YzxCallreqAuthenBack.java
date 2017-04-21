package com.hc360.mobileaccount.po;

public class YzxCallreqAuthenBack {

	/**
	 * 返回错误码，0：成功，非0：失败;
	 * 注：该错误码支持开发者自定义错误码，并且透传至应用层，方便AS与客户端之间的通讯。
	 * 开发者自定义错误码的规则是：int类型：10000~20000范围之间。
	 */
	public int retcode;
	public String record;//是否录音；0：不录音；1：录音；默认为0。
	
	
	public int getRetcode() {
		return retcode;
	}
	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
}

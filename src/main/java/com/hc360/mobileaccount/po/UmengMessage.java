package com.hc360.mobileaccount.po;//

public class UmengMessage {

	private Long fromUserid;
	private Long toUserid;
	private String fromPhone;//发送方手机号
	private String toPhone;//接收方手机号
	private String ticker;//通知栏要显示的文字
	private String title;//标题
	private String text;//内容
	private String activity;//行为
	private String custom;//自定义信息
	private String extraJsonStr;//扩展信息
	private Integer type;//消息类型  0通用通知 1自定义通知
	
	
	public String getFromPhone() {
		return fromPhone;
	}
	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}
	public String getToPhone() {
		return toPhone;
	}
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public String getExtraJsonStr() {
		return extraJsonStr;
	}
	public void setExtraJsonStr(String extraJsonStr) {
		this.extraJsonStr = extraJsonStr;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getFromUserid() {
		return fromUserid;
	}
	public void setFromUserid(Long fromUserid) {
		this.fromUserid = fromUserid;
	}
	public Long getToUserid() {
		return toUserid;
	}
	public void setToUserid(Long toUserid) {
		this.toUserid = toUserid;
	}
	
	
	
}

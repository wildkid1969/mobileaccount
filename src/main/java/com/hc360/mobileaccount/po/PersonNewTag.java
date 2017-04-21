package com.hc360.mobileaccount.po;

public class PersonNewTag {

	/**
	 * 主键
	 * */
	private int id;
	/**
	 * 注册用户的手机号
	 * */
	private String phone;

	/**
	 * 公司id
	 * */
	private String corpid;

	/**
	 * 手机号对应的姓名或公司名称
	 * */
	private String name;
	/**
	 * 标记日期
	 * */
	private String tagdate;
	/**
	 * 对方手机号
	 * */
	private String tagphone;
	/**
	 * 拨打次数
	 * */
	private String callcount;
	/**
	 * 定时提醒 1 开启 0 未开启
	 * */
	private String alarm;
	/**
	 * 定时提醒日期
	 * */
	private String alarmdate;
	/**
	 * 标签
	 * */
	private String tag;
	/**
	 * 是否已经发送 0 未发送 1 发送
	 * */
	private String selfdata1 = "0";
	/**
	 * 预留字段2
	 * */
	private String selfdata2;
	/**
	 * 客户端主键
	 * */
	private String cmid;
	/**
	 * 联系人姓名
	 */
	private String pname;
	private String tagremark = "";
	private String createtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCorpid() {
		return corpid;
	}
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTagdate() {
		return tagdate;
	}
	public void setTagdate(String tagdate) {
		this.tagdate = tagdate;
	}
	public String getTagphone() {
		return tagphone;
	}
	public void setTagphone(String tagphone) {
		this.tagphone = tagphone;
	}
	public String getCallcount() {
		return callcount;
	}
	public void setCallcount(String callcount) {
		this.callcount = callcount;
	}
	public String getAlarm() {
		return alarm;
	}
	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
	public String getAlarmdate() {
		return alarmdate;
	}
	public void setAlarmdate(String alarmdate) {
		this.alarmdate = alarmdate;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getSelfdata1() {
		return selfdata1;
	}
	public void setSelfdata1(String selfdata1) {
		this.selfdata1 = selfdata1;
	}
	public String getSelfdata2() {
		return selfdata2;
	}
	public void setSelfdata2(String selfdata2) {
		this.selfdata2 = selfdata2;
	}
	public String getCmid() {
		return cmid;
	}
	public void setCmid(String cmid) {
		this.cmid = cmid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getTagremark() {
		return tagremark;
	}
	public void setTagremark(String tagremark) {
		this.tagremark = tagremark;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}

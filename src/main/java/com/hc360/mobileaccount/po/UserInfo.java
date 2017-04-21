package com.hc360.mobileaccount.po;

public class UserInfo {
	private int id;
	private int corpid; //对应公司ID
	private String corpLinkman; // 联系人
	private String sex; // 性别
	private String duty; // 职务
	private String corpLinkmanPhone; // 公司联系方式
	private String mobilephone; // 移动电话
	private String fax; // 传真
	private String email; //email
	private String QQ;
	private String website; // 网站地址
	private String websiteType; // 网站类型
	private String linkSourceUrl;
	private String updatetime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCorpid() {
		return corpid;
	}
	public void setCorpid(int corpid) {
		this.corpid = corpid;
	}
	public String getCorpLinkman() {
		return corpLinkman;
	}
	public void setCorpLinkman(String corpLinkman) {
		this.corpLinkman = corpLinkman;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getCorpLinkmanPhone() {
		return corpLinkmanPhone;
	}
	public void setCorpLinkmanPhone(String corpLinkmanPhone) {
		this.corpLinkmanPhone = corpLinkmanPhone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getWebsiteType() {
		return websiteType;
	}
	public void setWebsiteType(String websiteType) {
		this.websiteType = websiteType;
	}
	public String getLinkSourceUrl() {
		return linkSourceUrl;
	}
	public void setLinkSourceUrl(String linkSourceUrl) {
		this.linkSourceUrl = linkSourceUrl;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
}

package com.hc360.mobileaccount.po;

public class CorpInfo {

	private long corpid = 0; // 企业ID
	private String corpname=""; // 企业名全称
	private String address=""; // 注册地址
	private String businesslicensecode=""; // 营业执照编号
	private String legalperson=""; // 法人姓名
	private String createTime=""; // 成立日期
	private String keepphone=""; // 工商留存电话
	private String registeredcapital=""; // 注册资本
	private String currencytype=""; // 资金币种
	private String mainproducts=""; // 主营产品和服务
	private String mainarea=""; // 主营行业
	private String businessaddress=""; // 经营地址
	private String specialmarket=""; // 专业市场
	private int enterpriseType=0; // 企业类型
	private String corplinkman=""; // 联系人姓名
	private String longitude=""; // 经度
	private String latitude=""; // 纬度
	private String logindate; // 注册时间
	private String organizationcode=""; // 组织机构代码证编号
	private String areaid=""; // 经营范围
	private String operatingstatus=""; // 经营状态
	private String brandname=""; // 品牌名称
	private String personmessage=""; // 姓名 ,电话,手机,
	private String searchdate=""; // 数据插入时间
	private String searchstate=""; // 数据加载状态
	private String loadstates=""; // 数据锁定状态
	private String datagrade=""; // 数据质量级别（一共为三级：有工商有电话为001，有工商，无电话为002，其它为003
	private int qualitygrade=0; // 质量级别（有工商有电话5，有工商无电话1，其它为0
	private String province=""; // 省
	private String city=""; // 市
	private String ismodify="0"; // 是否修改过默认0 修改1
	private String iscomm="0"; // 是否接通默认0 接通1 未接通2
	private String sourcequality=""; // 来源数据质量状态为1.慧聪付费
									// 2：人工录入、网络114、行业发行库、网上申请、阿里付费、3：4：无来源值其它来源、阿里抓取
	private String introduce = ""; // 公司简介
	private String username = ""; // 会员id
	public long getCorpid() {
		return corpid;
	}
	public void setCorpid(long corpid) {
		this.corpid = corpid;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusinesslicensecode() {
		return businesslicensecode;
	}
	public void setBusinesslicensecode(String businesslicensecode) {
		this.businesslicensecode = businesslicensecode;
	}
	public String getLegalperson() {
		return legalperson;
	}
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getKeepphone() {
		return keepphone;
	}
	public void setKeepphone(String keepphone) {
		this.keepphone = keepphone;
	}
	public String getRegisteredcapital() {
		return registeredcapital;
	}
	public void setRegisteredcapital(String registeredcapital) {
		this.registeredcapital = registeredcapital;
	}
	public String getCurrencytype() {
		return currencytype;
	}
	public void setCurrencytype(String currencytype) {
		this.currencytype = currencytype;
	}
	public String getMainproducts() {
		return mainproducts;
	}
	public void setMainproducts(String mainproducts) {
		this.mainproducts = mainproducts;
	}
	public String getMainarea() {
		return mainarea;
	}
	public void setMainarea(String mainarea) {
		this.mainarea = mainarea;
	}
	public String getBusinessaddress() {
		return businessaddress;
	}
	public void setBusinessaddress(String businessaddress) {
		this.businessaddress = businessaddress;
	}
	public String getSpecialmarket() {
		return specialmarket;
	}
	public void setSpecialmarket(String specialmarket) {
		this.specialmarket = specialmarket;
	}
	public int getEnterpriseType() {
		return enterpriseType;
	}
	public void setEnterpriseType(int enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	public String getCorplinkman() {
		return corplinkman;
	}
	public void setCorplinkman(String corplinkman) {
		this.corplinkman = corplinkman;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLogindate() {
		return logindate;
	}
	public void setLogindate(String logindate) {
		this.logindate = logindate;
	}
	public String getOrganizationcode() {
		return organizationcode;
	}
	public void setOrganizationcode(String organizationcode) {
		this.organizationcode = organizationcode;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getOperatingstatus() {
		return operatingstatus;
	}
	public void setOperatingstatus(String operatingstatus) {
		this.operatingstatus = operatingstatus;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getPersonmessage() {
		return personmessage;
	}
	public void setPersonmessage(String personmessage) {
		this.personmessage = personmessage;
	}
	public String getSearchdate() {
		return searchdate;
	}
	public void setSearchdate(String searchdate) {
		this.searchdate = searchdate;
	}
	public String getSearchstate() {
		return searchstate;
	}
	public void setSearchstate(String searchstate) {
		this.searchstate = searchstate;
	}
	public String getLoadstates() {
		return loadstates;
	}
	public void setLoadstates(String loadstates) {
		this.loadstates = loadstates;
	}
	public String getDatagrade() {
		return datagrade;
	}
	public void setDatagrade(String datagrade) {
		this.datagrade = datagrade;
	}
	public int getQualitygrade() {
		return qualitygrade;
	}
	public void setQualitygrade(int qualitygrade) {
		this.qualitygrade = qualitygrade;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIsmodify() {
		return ismodify;
	}
	public void setIsmodify(String ismodify) {
		this.ismodify = ismodify;
	}
	public String getIscomm() {
		return iscomm;
	}
	public void setIscomm(String iscomm) {
		this.iscomm = iscomm;
	}
	public String getSourcequality() {
		return sourcequality;
	}
	public void setSourcequality(String sourcequality) {
		this.sourcequality = sourcequality;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
